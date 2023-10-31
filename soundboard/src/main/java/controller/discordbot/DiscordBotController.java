package controller.discordbot;

import controller.API;
import model.DiscordBot;
import view.discordbot.DiscordBotView;

import java.util.ArrayList;
import java.util.Collection;

public class DiscordBotController implements ServiceController{

    private final DiscordBot model;
    private final DiscordBotView view;
    private final Collection<ServiceController> controllers;

    public DiscordBotController(DiscordBot discordBot, DiscordBotView discordBotView){
        model = discordBot;
        view = discordBotView;
        controllers = new ArrayList<>();
    }

    @Override
    public void sync() {
        controllers.forEach(ServiceController::sync);
    }

    @Override
    public void connect(API api) {
        controllers.forEach( c -> c.connect(api));
    }
}
