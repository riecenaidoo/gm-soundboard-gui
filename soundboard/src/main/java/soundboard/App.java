package soundboard;

import com.formdev.flatlaf.FlatDarkLaf;
import picocli.CommandLine;
import soundboard.controller.MenuBarController;
import soundboard.controller.RequestHandler;
import soundboard.controller.SoundboardController;
import soundboard.model.ClientSocket;
import soundboard.model.DiscordBot;
import soundboard.model.Icons;
import soundboard.model.catalogue.Catalogue;
import soundboard.view.MenuBar;
import soundboard.view.SoundboardView;

import javax.swing.*;
import java.util.Optional;

public class App {

    private final static String CATALOGUE_FILE_PATH = "docs/catalogue_sample.json";
    private final static String HOSTNAME = "0.0.0.0";
    private final static int PORT = 5000;

    private final DiscordBot discordBot;
    private final Catalogue catalogue;
    private final MenuBar menuBar;
    private final SoundboardView soundboardView;
    private final SoundboardController soundboardController;
    private ClientSocket clientSocket;
    private RequestHandler requestHandler;
    private JFrame app;

    @CommandLine.Option(names = {"-f", "--file"}, description = "Path to the catalogue file (json).")
    private String catalogueFilePath;
    @CommandLine.Option(names = {"-h", "--host"}, description = "Hostname / server ip the websocket is hosted on.")
    private String hostname;
    @CommandLine.Option(names = {"-p", "--port"}, description = "Port the websocket is hosted on.")
    private int port;

    private App() {
        catalogueFilePath = CATALOGUE_FILE_PATH;
        hostname = HOSTNAME;
        port = PORT;

        catalogue = new Catalogue();
        discordBot = new DiscordBot();

        soundboardView = new SoundboardView();
        soundboardController = new SoundboardController(this, soundboardView);

        menuBar = new MenuBar();
        new MenuBarController(this, menuBar);
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        App app = new App();
        new CommandLine(app).parseArgs(args);
        app.run();
    }

    /**
     * |Blocking| Continuously listen for messages over the ClientSocket.
     * <br><br>
     * Updates App view when no messages are being received, i.e. Socket has likely disconnected.
     * <br><br>
     * TODO Messages received will be used to update the App's views.
     */
    private void listen() {
        Optional<String> receivedMessage;
        do {
            receivedMessage = this.clientSocket.receive();
        } while (receivedMessage.isPresent());
        viewHome();
    }

    public void connectClient(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
        requestHandler = new RequestHandler(clientSocket);
        new Thread(this::listen).start();
    }

    public void disconnectClient() {
        clientSocket.disconnect();
        clientSocket = null;
        requestHandler = null;
    }

    public DiscordBot getDiscordBot() {
        return discordBot;
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public int getPort() {
        return port;
    }

    public String getHostname() {
        return hostname;
    }

    public void viewDiscordBot() {
        soundboardController.connect(requestHandler);
        app.setJMenuBar(menuBar);
        soundboardView.viewDiscordBot();
        app.pack();
    }

    public void viewHome() {
        app.setJMenuBar(null);
        soundboardController.disconnect();
        soundboardView.viewHome();
        app.pack();
    }

    /**
     * Initialise data of the Catalogue & DiscordBot, and sync their views.
     */
    public void initialise() {
        if (!catalogue.load(catalogueFilePath)) catalogue.loadTemplate();
        soundboardController.getCatalogueController().load();
        soundboardController.loadIcons(new Icons());

        discordBot.setDummyValues();
        soundboardController.getDiscordBotController().sync();
    }

    public void run() {
        initialise();
        //Create and set up the window.
        app = new JFrame("Soundboard");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setContentPane(soundboardView);
        app.pack();
        app.setVisible(true);
    }
}
