package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Categories are used to organise groups of Playlists together.
 * A Category object represents an ordered collection of Playlists.
 */
class Category {

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
}