package soundboard;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.FlatDarkLaf;
import soundboard.controller.HomeController;
import soundboard.controller.MenuBarController;
import soundboard.controller.RequestHandler;
import soundboard.controller.SoundboardController;
import soundboard.model.ClientSocket;
import soundboard.model.DiscordBot;
import soundboard.model.Icons;
import soundboard.model.catalogue.Catalogue;
import soundboard.view.HomeView;
import soundboard.view.MenuBar;
import soundboard.view.SoundboardView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class App {

    private final static String CATALOGUE = "docs/catalogue_sample.json";

    private final DiscordBot discordBot;
    private final Catalogue catalogue;
    private final MenuBar menuBar;
    private final HomeView homeView;
    private final SoundboardView soundboardView;
    private final SoundboardController soundboardController;
    private ClientSocket clientSocket;
    private RequestHandler requestHandler;
    private JFrame app;

    private App() {
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

        soundboardView = new SoundboardView();
        soundboardController = new SoundboardController(this, soundboardView);
        soundboardController.loadIcons(icons);

        homeView = new HomeView();
        new HomeController(this, homeView);

        menuBar = new MenuBar();
        new MenuBarController(this, menuBar);
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        new App().run();
    }

    public void setClient(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public DiscordBot getDiscordBot() {
        return discordBot;
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public void openSoundboard() {
        requestHandler = new RequestHandler(clientSocket);
        soundboardController.connect(requestHandler);
        app.setJMenuBar(menuBar);
        app.setContentPane(soundboardView);
        app.pack();
    }

    public void closeSoundboard() {
        clientSocket.disconnect();
        clientSocket = null;
        requestHandler = null;
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
