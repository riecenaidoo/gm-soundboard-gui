package soundboard.model.catalogue;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

/**
 * Named, ordered, collection of Playlists in the Catalogue.
 */
public class Group extends ArrayList<Playlist> {

    private final String name;

    protected Group(String name) {
        this.name = name;
    }

    public static Group fromJson(JsonNode json) {
        Group group = new Group(json.get("Group").asText());
        for (JsonNode playlist : json.get("Items")) group.add(Playlist.fromJson(playlist));
        return group;
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

    public JsonNode toJson() {
        throw new UnsupportedOperationException("TODO");
    }
}