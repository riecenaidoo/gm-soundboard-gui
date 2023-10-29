package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.FlatDarkLaf;
import model.Catalogue;
import model.Icons;
import view.CatalogueTabbedPane;
import view.ChannelSelectorPanel;
import view.MusicPlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Soundboard {

    final static String CATALOGUE = "sample_catalogue.json";

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
        try {
            Catalogue catalogue = Catalogue.fromJson(mapper.readTree(new File(CATALOGUE)));
            CatalogueTabbedPane catalogueTabbedPane = new CatalogueTabbedPane();
            new CatalogueController(api, catalogue, catalogueTabbedPane);
            panel.add(catalogueTabbedPane);
        } catch (IOException e) {
            // Fallback: Read from template.
            //InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("mock_catalogue.json")) {
            //ObjectMapper mapper = new ObjectMapper();
            //JsonNode arrayNode = mapper.readValue(in, JsonNode.class);
            throw new RuntimeException("There was a problem parsing '%s' as a JSON file.\\n\", CATALOGUE.");
        }

        JPanel mediaPanel = new JPanel();

        MusicPlayerPanel miniPlayer = new MusicPlayerPanel(api, icons);
        mediaPanel.add(miniPlayer);

        ChannelSelectorPanel channelSelector = new ChannelSelectorPanel(api, icons);
        channelSelector.populateChannelList(new String[]{"0", "1", "3", "4", "5", "6", "7", "8", "9"});
        mediaPanel.add(channelSelector);

        panel.add(mediaPanel);
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
