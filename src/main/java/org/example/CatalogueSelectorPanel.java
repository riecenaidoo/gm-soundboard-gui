package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * Singleton container class for the Channel Selector portion of the interface.
 */
public class CatalogueSelectorPanel extends JPanel {

    private static CatalogueSelectorPanel INSTANCE;

    private CatalogueSelectorPanel() {
        super();
        JTabbedPane tabbedPane = new JTabbedPane();

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

        this.add(tabbedPane);
    }

    public static CatalogueSelectorPanel getCatalogueSelector() {
        if (INSTANCE == null) INSTANCE = new CatalogueSelectorPanel();
        return INSTANCE;
    }
}
