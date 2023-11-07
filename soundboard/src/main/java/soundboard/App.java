package soundboard;

import com.formdev.flatlaf.FlatDarkLaf;
import picocli.CommandLine;
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

public class App {

    private final DiscordBot discordBot;
    private final Catalogue catalogue;
    private final MenuBar menuBar;
    private final HomeView homeView;
    private final SoundboardView soundboardView;
    private final SoundboardController soundboardController;
    private ClientSocket clientSocket;
    private RequestHandler requestHandler;
    private JFrame app;

    @CommandLine.Option(names = {"-f", "--file"}, description = "Path to the catalogue file (json).")
    private String catalogueFilePath;
    @CommandLine.Option(names = {"-p", "--port"}, description = "Port the websocket is hosted on.")
    private int port;

    private App() {
        //        catalogue = Catalogue.fromFile("docs/catalogue_sample.json");
        catalogue = new Catalogue();
        discordBot = new DiscordBot().dummyValues();

        soundboardView = new SoundboardView();
        soundboardController = new SoundboardController(this, soundboardView);
        soundboardController.loadIcons(new Icons());

        homeView = new HomeView();
        new HomeController(this, homeView);

        menuBar = new MenuBar();
        new MenuBarController(this, menuBar);
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        App app = new App();
        new CommandLine(app).parseArgs(args);
        app.run();
    }

    public void connectClient(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
        requestHandler = new RequestHandler(clientSocket);
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

    public void viewSoundboard() {
        soundboardController.connect(requestHandler);
        app.setJMenuBar(menuBar);
        app.setContentPane(soundboardView);
        app.pack();
    }

    public void viewHome() {
        app.setJMenuBar(null);
        app.setContentPane(homeView);
        app.pack();
    }

    public void run() {
        //Create and set up the window.
        //Load the catalogue
//        catalogue.load(catalogueFilePath);
        app = new JFrame("Soundboard");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setContentPane(homeView);
        app.pack();
        app.setVisible(true);
    }
}
