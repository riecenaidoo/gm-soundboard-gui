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

    public String getTitle() {
        return title;
    }

    public Collection<String> getSongs() {
        return songs;
    }

    /**
     * Note: Playlist does not allow duplications of Songs.
     */
    public boolean add(String song) {
        return songs.add(song);
    }

    public boolean remove(String song) {
        return songs.remove(song);
    }

    /**
     * @return number of Songs in this Playlist.
     */
    public int size() {
        return songs.size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        s.append(title);
        s.append(":[");
        songs.forEach(song -> {
            s.append(song);
            s.append(", ");
        });
        s.deleteCharAt(s.lastIndexOf(", "));
        s.append("]}");

        return s.toString();
    }
}