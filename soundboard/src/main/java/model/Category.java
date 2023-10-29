package model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Categories are used to organise groups of Playlists together.
 * A Category object represents an ordered collection of Playlists.
 */
public class Category {

    private final String title;
    private final Collection<Playlist> playlists;

    public Category(String title) {
        this.title = title;
        playlists = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public Collection<Playlist> getPlaylists() {
        return playlists;
    }

    public boolean add(Playlist playlist) {
        return playlists.add(playlist);
    }

    public boolean remove(Playlist playlist) {
        return playlists.remove(playlist);
    }

    /**
     * @return number of Playlists in this Category.
     */
    public int size() {
        return playlists.size();
    }

    /**
     * @return total number of Songs in this Category.
     */
    public int total() {
        return playlists.stream().mapToInt(Playlist::size).sum();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        s.append(title);
        s.append(":[");
        playlists.forEach(playlist -> {
            s.append(playlist.getTitle());
            s.append(", ");
        });
        s.deleteCharAt(s.lastIndexOf(", "));
        s.append("]}");

        return s.toString();
    }

    public static Category fromJson(JsonNode json) {
        Category category = new Category(json.get("Group").asText());
        for (JsonNode playlist : json.get("Items")) category.add(Playlist.fromJson(playlist));
        return category;
    }

    public JsonNode toJson(Category category) {
        throw new UnsupportedOperationException("TODO");
    }
}