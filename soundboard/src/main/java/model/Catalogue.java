package model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

/**
 * The Catalogue represents all playlists,
 * sorted by category, that have been loaded into this application.
 */
public class Catalogue extends ArrayList<CatalogueGroup> {

    public static Catalogue fromJson(JsonNode json) {
        Catalogue catalogue = new Catalogue();
        for (JsonNode category : json) catalogue.add(CatalogueGroup.fromJson(category));
        return catalogue;
    }

    /**
     * @return total number of Songs in this Catalogue.
     */
    public int total() {
        return this.stream().mapToInt(CatalogueGroup::total).sum();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        this.forEach(catalogueGroup -> {
            s.append(catalogueGroup.getName());
            s.append(", ");
        });
        s.deleteCharAt(s.lastIndexOf(", "));
        s.append("]");

        return s.toString();
    }

    public JsonNode toJson(Catalogue catalogue) {
        throw new UnsupportedOperationException("TODO");
    }
}
