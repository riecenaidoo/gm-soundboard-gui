package model;

import java.util.Collection;
import java.util.HashSet;

/**
 * A Playlist represents a collection of songs.
 * Specifically, YouTube URL to songs for the discord bot to play from.
 * In the future, this may expand to additional services such as Deezer,
 * Spotify, or local songs.
 */
class Playlist {

    private final String title;
    private final Collection<String> songs;

    public Playlist(String title) {
        this.title = title;
        this.songs = new HashSet<>();
    }
}