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
        this.playlists = new ArrayList<>();
    }

    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }
}