package soundboard.controller;

import soundboard.App;
import soundboard.controller.catalogue.CatalogueController;
import soundboard.controller.discordbot.DiscordBotController;
import soundboard.controller.discordbot.DiscordBotControllersList;
import soundboard.model.Icons;
import soundboard.view.SoundboardView;

public class SoundboardController {

    private final CatalogueController catalogueController;
    private final DiscordBotController discordBotController;

    public SoundboardController(App model, SoundboardView view) {
        catalogueController = new CatalogueController(model.getCatalogue(), view.getCatalogueView());
        discordBotController = new DiscordBotControllersList(model.getDiscordBot(), view.getDiscordBotView());
        catalogueController.load();
        discordBotController.sync();
    }

    public void loadIcons(Icons icons) {
        discordBotController.loadIcons(icons);
        discordBotController.sync();
    }

    public void connect(RequestHandler requestHandler) {
        catalogueController.connect(requestHandler);
        discordBotController.connect(requestHandler);
    }
}