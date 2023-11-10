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

    public void load(JsonNode catalogueNode) {
        for (JsonNode group : catalogueNode) add(Group.fromJson(group));
    }

    public boolean load(String catalogueJsonFile) {
        try {
            JsonNode catalogueNode = new ObjectMapper().readTree(new File(catalogueJsonFile));
            load(catalogueNode);
            return true;
        } catch (IOException e) {
            System.out.printf("[WARNING] Could not generate a catalogue from file '%s': %s.\n", catalogueJsonFile, e.getMessage());
            return false;
        }
    }

    public void loadTemplate() {
        InputStream in = Thread.currentThread().getContextClassLoader().
                getResourceAsStream("catalogue_template.json");
        try {
            JsonNode catalogueNode = (new ObjectMapper().readValue(in, JsonNode.class));
            load(catalogueNode);
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
