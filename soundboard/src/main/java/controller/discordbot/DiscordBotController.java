package controller.discordbot;

import controller.API;
import model.DiscordBot;
import model.Icons;
import view.discordbot.DiscordBotView;

import java.util.HashMap;
import java.util.Map;

public class DiscordBotController implements ServiceController{

    private final DiscordBot model;
    private final DiscordBotView view;
    private final Map<String, ServiceController> controllers;

    public DiscordBotController(DiscordBot discordBot, DiscordBotView discordBotView){
        model = discordBot;
        view = discordBotView;
        controllers = new HashMap<>();
        controllers.put("volume", new VolumeController(discordBot, view.getVolumeControl()));
//        controllers.put("channels", new ChannelsController(discordBot, view.getChannelsPanel()));
    }

    @Override
    public void sync() {
        controllers.values().forEach(ServiceController::sync);
    }

    @Override
    public void connect(API api) {
        controllers.values().forEach(c -> c.connect(api));
    }

    @Override
    public void loadIcons(Icons icon) {
        controllers.values().forEach(c -> c.loadIcons(icon));
    }
}
