package soundboard.model.catalogue;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

/**
 * Represents a collection of songs.
 * <br><br>
 * Specifically, YouTube URLs to songs.
 * These URLs are sent to the Bot service where they are used to retrieve
 * audio sources to stream into a Discord Voice Channel.
 * <br><br>
 * In the future, the Bot may expand to additional services such as Deezer,
 * Spotify, or local songs. In which case this Playlist will store
 * Song objects that contain URLs and the name of the service the URL points to
 * so that the Bot can know how to handle retrieving the audio source at that URL.
 */
public class Playlist extends ArrayList<String> {

    private String title;

    public Playlist(String title) {
        this.title = title;
    }

    /**
     * @param json Node representing a Playlist.
     * @return Playlist constructed from the contents of a JSON node.
     */
    public static Playlist fromJson(JsonNode json) {
        Playlist playlist = new Playlist(json.get("Title").asText());
        for (JsonNode song : json.get("Songs")) playlist.add(song.asText());
        return playlist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
