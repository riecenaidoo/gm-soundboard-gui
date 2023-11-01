package controller;

import controller.catalogue.CatalogueController;
import controller.discordbot.DiscordBotController;
import controller.discordbot.DiscordBotControllersList;
import model.Icons;
import view.SoundboardPanel;

public class SoundboardController {

    private final CatalogueController catalogueController;
    private final DiscordBotController discordBotController;

    public SoundboardController(Soundboard model, SoundboardPanel view) {
        catalogueController = new CatalogueController(model.getCatalogue(), view.getCatalogueView());
        discordBotController = new DiscordBotControllersList(model.getDiscordBot(), view.getDiscordBotView());
        catalogueController.load();
        discordBotController.sync();
    }

    public void loadIcons(Icons icons) {
        discordBotController.loadIcons(icons);
        discordBotController.sync();
    }

    public void connect(API api) {
        catalogueController.connect(api);
        discordBotController.connect(api);
    }
}
