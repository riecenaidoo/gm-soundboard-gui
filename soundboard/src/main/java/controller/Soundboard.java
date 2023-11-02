package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.FlatDarkLaf;
import model.DiscordBot;
import model.Icons;
import model.catalogue.Catalogue;
import view.HomePanel;
import view.MenuBar;
import view.SoundboardPanel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Soundboard {

    private final static String CATALOGUE = "docs/catalogue_sample.json";

    private Client client;
    private API api;

    private final DiscordBot discordBot;
    private final Catalogue catalogue;

    private final MenuBar menuBar;

    private final HomePanel homeView;
    private final SoundboardPanel soundboardView;
    private JFrame app;
    private final SoundboardController soundboardController;

    private Soundboard() {
        Icons icons = new Icons();

        ObjectMapper mapper = new ObjectMapper();
        try {
            if (!CATALOGUE.isEmpty()) {
                catalogue = Catalogue.fromJson(mapper.readTree(new File(CATALOGUE)));
            } else {
                InputStream in = Thread.currentThread().getContextClassLoader().
                        getResourceAsStream("catalogue_template.json");
                catalogue = Catalogue.fromJson(mapper.readValue(in, JsonNode.class));
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        discordBot = new DiscordBot();
        discordBot.setVoiceChannels(List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        discordBot.setVolume(50);

        soundboardView = new SoundboardPanel();
        soundboardController = new SoundboardController(this, soundboardView);
        soundboardController.loadIcons(icons);

        homeView = new HomePanel();
        new HomeController(this, homeView);

        menuBar = new MenuBar();
        new MenuBarController(this, menuBar);
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        new Soundboard().run();
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public DiscordBot getDiscordBot() {
        return discordBot;
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public void openSoundboard() {
        api = new API(client);
        soundboardController.connect(api);
        app.setJMenuBar(menuBar);
        app.setContentPane(soundboardView);
        app.pack();
    }

    public void closeSoundboard() {
        client.disconnect();
        client = null;
        api = null;
        app.setJMenuBar(null);
        app.setContentPane(homeView);
        app.pack();
    }

    public void run() {
        //Create and set up the window.
        app = new JFrame("Soundboard");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setContentPane(homeView);
        app.pack();
        app.setVisible(true);
    }
}
