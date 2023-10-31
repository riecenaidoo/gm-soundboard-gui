package controller.discordbot;

import controller.API;
import model.DiscordBot;
import model.Icons;
import view.discordbot.DiscordBotView;

import java.util.ArrayList;

public class DiscordBotControllersList extends ArrayList<DiscordBotController> implements DiscordBotController {

    public DiscordBotControllersList(DiscordBot discordBot, DiscordBotView discordBotView) {
        super();
        this.add(new VolumeSliderController(discordBot, discordBotView.getVolumeControl()));
        this.add(new ChannelSelectorController(discordBot, discordBotView.getChannelsPanel()));
        // Song Controls
        this.add(new LoopToggleController(discordBot, discordBotView.getSongControlsPanel()));
        this.add(new PlayToggleController(discordBot, discordBotView.getSongControlsPanel()));
        this.add(new PrevButtonController(discordBot, discordBotView.getSongControlsPanel()));
        this.add(new ShuffleToggleController(discordBot, discordBotView.getSongControlsPanel()));
        this.add(new SkipButtonController(discordBot, discordBotView.getSongControlsPanel()));
        this.add(new StopButtonController(discordBot, discordBotView.getSongControlsPanel()));
    }

    public void sync() {
        this.forEach(DiscordBotController::sync);
    }

    public void connect(API api) {
        this.forEach(c -> c.connect(api));
    }

    public void loadIcons(Icons icon) {
        this.forEach(c -> c.loadIcons(icon));
    }
}
