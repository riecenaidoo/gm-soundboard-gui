package soundboard.controller;

import soundboard.App;
import soundboard.controller.catalogue.CatalogueController;
import soundboard.controller.discordbot.DiscordBotController;
import soundboard.controller.discordbot.DiscordBotControllersList;
import soundboard.controller.searchbar.SearchBarController;
import soundboard.model.Icons;
import soundboard.view.SoundboardView;

public class SoundboardController {

    private final CatalogueController catalogueController;
    private final HomeController homeController;
    private final DiscordBotController discordBotController;
    private final SearchBarController searchbarController;

    public SoundboardController(App model, SoundboardView view) {
        catalogueController = new CatalogueController(model.getCatalogue(), view.getCatalogueView());
        homeController = new HomeController(model, view.getHomeView());
        discordBotController = new DiscordBotControllersList(model.getDiscordBot(), view.getDiscordBotView());
        searchbarController = new SearchBarController(view.getSearchBarView());
    }

    public void loadIcons(Icons icons) {
        discordBotController.loadIcons(icons);
        discordBotController.sync();
    }

    public void connect(RequestHandler requestHandler) {
        catalogueController.connect(requestHandler);
        discordBotController.connect(requestHandler);
        searchbarController.connect(requestHandler);
    }

    public void disconnect() {
        catalogueController.disconnect();
        searchbarController.disconnect();
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
