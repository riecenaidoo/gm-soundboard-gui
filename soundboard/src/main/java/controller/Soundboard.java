package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.FlatDarkLaf;
import controller.catalogue.CatalogueController;
import controller.discordbot.DiscordBotController;
import controller.discordbot.DiscordBotControllersList;
import model.DiscordBot;
import model.Icons;
import model.catalogue.Catalogue;
import view.HomePanel;
import view.catalogue.CatalogueView;
import view.discordbot.DiscordBotView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Soundboard {

    private final static String CATALOGUE = "docs/catalogue_sample.json";

    private final Icons icons;
    private final JFrame app;
    private final HomePanel home;
    private Client client;
    private API api;
    private JPanel soundboard;

    private Soundboard() {
        icons = new Icons();
        home = new HomePanel();
        new HomeController(this, home);
        //Create and set up the window.
        app = new JFrame("Soundboard");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setContentPane(home);
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        new Soundboard().run();
    }

    public void setClient(Client client) {
        this.client = client;
    }

    private JPanel buildSoundboard() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        ObjectMapper mapper = new ObjectMapper();
        Catalogue catalogue;
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

        CatalogueView catalogueView = new CatalogueView();
        panel.add(catalogueView);

        CatalogueController catalogueController = new CatalogueController(catalogue, catalogueView);
        catalogueController.load();
        catalogueController.connect(api);

        DiscordBot discordBot = new DiscordBot();
        discordBot.setVoiceChannels(List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        discordBot.setVolume(50);

        DiscordBotView discordBotView = new DiscordBotView();
        panel.add(discordBotView);

        DiscordBotController discordBotController = new DiscordBotControllersList(discordBot, discordBotView);
        discordBotController.loadIcons(icons);
        discordBotController.sync();
        discordBotController.connect(api);

        return panel;
    }

    public void openSoundboard() {
        api = new API(client);
        soundboard = buildSoundboard();
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem disconnect = new JMenuItem("Disconnect");
        disconnect.addActionListener(e -> closeSoundboard());
        menu.add(disconnect);
        menuBar.add(menu);
        app.setJMenuBar(menuBar);
        app.setContentPane(soundboard);
        app.pack();
    }

    public void closeSoundboard() {
        client.disconnect();
        client = null;
        api = null;
        app.setJMenuBar(null);
        app.setContentPane(home);
        app.pack();
        soundboard = null;
    }

    public void run() {
        //Display the window.
        app.pack();
        app.setVisible(true);
    }
}
