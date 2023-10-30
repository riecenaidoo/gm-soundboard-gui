package model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

/**
 * Named, ordered, collection of Playlists in the Catalogue.
 */
public class CatalogueGroup extends ArrayList<Playlist> {

    private final String name;

    public CatalogueGroup(String name) {
        this.name = name;
    }

    public static CatalogueGroup fromJson(JsonNode json) {
        CatalogueGroup catalogueGroup = new CatalogueGroup(json.get("Group").asText());
        for (JsonNode playlist : json.get("Items")) catalogueGroup.add(Playlist.fromJson(playlist));
        return catalogueGroup;
    }

    public String getName() {
        return name;
    }

    /**
     * @return total number of Songs in this Category.
     */
    public int total() {
        return this.stream().mapToInt(Playlist::size).sum();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        s.append(name);
        s.append(":[");
        this.forEach(playlist -> {
            s.append(playlist.getTitle());
            s.append(", ");
        });
        s.deleteCharAt(s.lastIndexOf(", "));
        s.append("]}");

        return s.toString();
    }

    public JsonNode toJson(CatalogueGroup catalogueGroup) {
        throw new UnsupportedOperationException("TODO");
    }
}