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
import view.catalogue.CatalogueView;
import view.discordbot.DiscordBotView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Soundboard {

    final static String CATALOGUE = "docs/catalogue_sample.json";

    Icons icons;
    Client client;
    API api;
    JPanel home;
    JPanel soundboard;
    JFrame app;

    private Soundboard() {
        icons = new Icons();
        home = buildHome();
        //Create and set up the window.
        app = new JFrame("Soundboard");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setContentPane(home);
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        new Soundboard().run();
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
        CatalogueController catalogueController = new CatalogueController(catalogue, catalogueView);
        catalogueController.load();
        catalogueController.connect(api);
        panel.add(catalogueView);

        DiscordBot discordBot = new DiscordBot();
        discordBot.setVoiceChannels(List.of("0", "1", "3", "4", "5", "6", "7", "8", "9"));
        DiscordBotView discordBotView = new DiscordBotView();
        DiscordBotController discordBotController = new DiscordBotControllersList(discordBot, discordBotView);
        discordBotController.loadIcons(icons);
        discordBotController.sync();
        discordBotController.connect(api);
        panel.add(discordBotView);

//        MusicPlayerPanel miniPlayer = new MusicPlayerPanel(api, icons);
//        mediaPanel.add(miniPlayer);

//        ChannelsPanel channelsPanel = new ChannelsPanel();
//        ChannelsServiceController channelsServiceController = new ChannelsServiceController(discordBot.getVoiceChannels(), channelsPanel);
//        channelsServiceController.sync();
//        channelsServiceController.loadIcons(icons);
//        channelsServiceController.connect(api);
//        mediaPanel.add(channelsPanel);

//        panel.add(mediaPanel);
        return panel;
    }

    private JPanel buildHome() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(250, 60));

        JLabel status = new JLabel();
        status.setPreferredSize(new Dimension(80, 20));
        JButton connect = new JButton("Connect");
        connect.setPreferredSize(new Dimension(50, 30));
        connect.addActionListener(e -> {
            try {
                client = Client.getClient(this);
                status.setText("Connected!");

                openSoundboard();
            } catch (Client.ClientCreationException c) {
                status.setText("Connection Failed.");
            }

            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println("Label clearing thread interrupted.");
                }
                status.setText("");
            }).start();
        });

        panel.add(connect, BorderLayout.CENTER);
        panel.add(status, BorderLayout.SOUTH);
        return panel;
    }

    private void openSoundboard() {
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
