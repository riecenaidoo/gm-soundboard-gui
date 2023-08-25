package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton container class for the Channel Selector portion of the interface.
 */
public class CatalogueSelectorPanel extends JPanel {

    private static CatalogueSelectorPanel INSTANCE;

    JTabbedPane tabbedPane;

    private CatalogueSelectorPanel() {
        super();
        tabbedPane = new JTabbedPane();
        this.add(tabbedPane);
    }

    public static CatalogueSelectorPanel getCatalogueSelector() {
        if (INSTANCE == null) INSTANCE = new CatalogueSelectorPanel();
        return INSTANCE;
    }


    public void loadUI(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode arrayNode = mapper.readTree(new File(filepath));
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
                    for (JsonNode song : songArray) {
                        songs.add(song.asText());
                    }

                    button.addActionListener(e -> API.play(songs));
                    groupPanel.add(button);
                }

                tabbedPane.add(groupName, groupPanel);
            }


        } catch (JsonProcessingException e) {
            System.out.printf("[WARNING] There was a problem parsing '%s'.\n", filepath);
            System.out.printf("\tReason: '%s'.\n", e.getMessage());
            loadDummyUI();
        } catch (IOException e) {
            System.out.printf("[WARNING] There was a problem opening '%s'.\n", filepath);
            System.out.printf("\tReason: '%s'.\n", e.getMessage());
            loadDummyUI();
        }
    }

    public void loadDummyUI() {
        JPanel gamePanel = new JPanel();
        gamePanel.add(new Button("Game"));
        gamePanel.add(new Button("Game"));
        gamePanel.add(new Button("Game"));

        JPanel combatPanel = new JPanel();
        combatPanel.add(new Button(("Combat")));
        combatPanel.add(new Button(("Combat")));
        combatPanel.add(new Button(("Combat")));

        JPanel moodPanel = new JPanel();
        moodPanel.add(new Button("Mood"));
        moodPanel.add(new Button("Mood"));
        moodPanel.add(new Button("Mood"));

        JPanel ambiencePanel = new JPanel();
        ambiencePanel.add(new Button("Ambience"));
        ambiencePanel.add(new Button("Ambience"));
        ambiencePanel.add(new Button("Ambience"));

        tabbedPane.addTab("GAME", gamePanel);
        tabbedPane.addTab("COMBAT", combatPanel);
        tabbedPane.addTab("MOOD", moodPanel);
        tabbedPane.addTab("AMBIENCE", ambiencePanel);
    }
}
