package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
            JsonNode jsonNode = mapper.readTree(new File(filepath));
            System.out.println(jsonNode.toPrettyString());
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
