package controller.discordbot;

import controller.API;
import model.DiscordBot;
import model.Icons;
import view.discordbot.DiscordBotView;

import java.util.HashMap;

public class DiscordBotController extends HashMap<String, ServiceController> {

    public DiscordBotController(DiscordBot discordBot, DiscordBotView discordBotView){
        super();
        this.put("volume", new VolumeServiceController(discordBot, discordBotView.getVolumeControl()));
        this.put("channels", new ChannelsServiceController(discordBot, discordBotView.getChannelsPanel()));
    }

    public void sync() {
        this.values().forEach(ServiceController::sync);
    }

    public void connect(API api) {
        this.values().forEach(c -> c.connect(api));
    }

    public void loadIcons(Icons icon) {
        this.values().forEach(c -> c.loadIcons(icon));
    }
}
