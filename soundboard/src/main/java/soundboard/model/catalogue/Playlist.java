package soundboard.model.catalogue;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

/**
 * A Playlist represents a collection of songs.
 * Specifically, YouTube URL to songs for the discord bot to play from.
 * In the future, this may expand to additional services such as Deezer,
 * Spotify, or local songs.
 */
public class Playlist extends ArrayList<String> {

    private final String title;

    protected Playlist(String title) {
        this.title = title;
    }

    public static Playlist fromJson(JsonNode json) {
        Playlist playlist = new Playlist(json.get("Title").asText());
        for (JsonNode song : json.get("Songs")) playlist.add(song.asText());
        return playlist;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        s.append(title);
        s.append(":[");
        this.forEach(song -> {
            s.append(song);
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
