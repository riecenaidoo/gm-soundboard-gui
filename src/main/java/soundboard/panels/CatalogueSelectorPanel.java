package soundboard.panels;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import soundboard.API;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton container class for the Channel Selector portion of the interface.
 */
public class CatalogueSelectorPanel extends JPanel {

    private final API api;

    private final JTabbedPane tabbedPane;

    public CatalogueSelectorPanel(API api) {
        super();
        this.api = api;
        this.tabbedPane = new JTabbedPane();

        this.add(tabbedPane);
    }


    public void loadUI(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode arrayNode = mapper.readTree(new File(filepath));
            buildUI(arrayNode);
        } catch (IOException e) {
            System.out.printf("[WARNING] There was a problem parsing '%s' as a JSON file.\n", filepath);
            System.out.printf("\tReason: '%s'.\n", e.getMessage());
            System.out.println("\t> Loading a mock catalogue instead.");
            loadMockUI();
        }
    }

    public void loadMockUI() {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("mock_catalogue.json")) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode arrayNode = mapper.readValue(in, JsonNode.class);
            buildUI(arrayNode);
        } catch (IOException e) {
            throw new RuntimeException("Mock Catalogue could not be read!");
        }
    }

    private void buildUI(JsonNode arrayNode) {
        if (!arrayNode.isArray())
            throw new RuntimeException("[WARNING] The structure of the JSON is not as expected!");

        for (JsonNode jsonNode : arrayNode) {
            JPanel groupPanel = new JPanel();
            String groupName = jsonNode.get("Group").asText();

            JsonNode itemNode = jsonNode.get("Items");
            if (!itemNode.isArray())
                throw new RuntimeException("[WARNING] The structure of the JSON is not as expected!");

            for (JsonNode item : itemNode) {
                String title = item.get("Title").asText();
                JButton button = new JButton(title);

                JsonNode songArray = item.get("Songs");
                if (!songArray.isArray())
                    throw new RuntimeException("[WARNING] The structure of the JSON is not as expected!");

                List<String> songs = new ArrayList<>();
                for (JsonNode song : songArray) songs.add(song.asText());
                button.addActionListener(e -> api.play(songs));
                groupPanel.add(button);
            }

            tabbedPane.add(groupName, groupPanel);
        }
    }
}
