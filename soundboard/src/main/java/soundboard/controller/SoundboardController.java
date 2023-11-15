package soundboard.controller;

import soundboard.App;
import soundboard.controller.catalogue.CatalogueController;
import soundboard.controller.discordbot.DiscordBotController;
import soundboard.controller.discordbot.DiscordBotControllersList;
import soundboard.model.Icons;
import soundboard.view.SoundboardView;

public class SoundboardController {

    private final CatalogueController catalogueController;
    private final HomeController homeController;
    private final DiscordBotController discordBotController;

    public SoundboardController(App model, SoundboardView view) {
        catalogueController = new CatalogueController(model.getCatalogue(), view.getCatalogueView());
        homeController = new HomeController(model, view.getHomeView());
        discordBotController = new DiscordBotControllersList(model.getDiscordBot(), view.getDiscordBotView());
    }

    public void loadIcons(Icons icons) {
        discordBotController.loadIcons(icons);
        discordBotController.sync();
    }

    public void connect(RequestHandler requestHandler) {
        catalogueController.connect(requestHandler);
        discordBotController.connect(requestHandler);
    }

    public CatalogueController getCatalogueController() {
        return catalogueController;
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public DiscordBotController getDiscordBotController() {
        return discordBotController;
    }
}
