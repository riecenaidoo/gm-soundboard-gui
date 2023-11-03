package soundboard.model.catalogue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    public static Catalogue fromFile(String catalogueJsonFile) {
        try {
            return Catalogue.fromJson(new ObjectMapper().readTree(new File(catalogueJsonFile)));
        } catch (IOException e) {
            System.out.printf("[WARNING] Could not generate a catalogue from file '%s': %s.\n", catalogueJsonFile, e.getMessage());
            System.out.println("[INFO] Generating catalogue from template instead.");
            return fromTemplate();
        }
    }

    public static Catalogue fromTemplate() {
        InputStream in = Thread.currentThread().getContextClassLoader().
                getResourceAsStream("catalogue_template.json");
        try {
            return Catalogue.fromJson(new ObjectMapper().readValue(in, JsonNode.class));
        } catch (IOException e) {
            System.out.println("[ERROR] There may be an issue with catalogue template resource.");
            throw new RuntimeException(e.getMessage());
        }
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
