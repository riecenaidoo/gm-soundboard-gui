package soundboard.model.catalogue;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

/**
 * The Catalogue represents all playlists,
 * sorted by category, that have been loaded into this application.
 */
public class Catalogue extends ArrayList<Group> {

    public static Catalogue fromJson(JsonNode json) {
        Catalogue catalogue = new Catalogue();
        for (JsonNode category : json) catalogue.add(Group.fromJson(category));
        return catalogue;
    }

    /**
     * @return total number of Songs in this Catalogue.
     */
    public int total() {
        return this.stream().mapToInt(Group::total).sum();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        this.forEach(group -> {
            s.append(group.getName());
            s.append(", ");
        });
        s.deleteCharAt(s.lastIndexOf(", "));
        s.append("]");

        return s.toString();
    }

    public JsonNode toJson() {
        throw new UnsupportedOperationException("TODO");
    }
}
