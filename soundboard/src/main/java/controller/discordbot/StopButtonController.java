package controller.discordbot;

import controller.API;
import model.DiscordBot;
import model.Icons;
import view.discordbot.SongControlsPanel;

import javax.swing.*;
import java.util.Arrays;

class StopButtonController implements DiscordBotController {

    private final DiscordBot model; // I'll be used in the future.
    private final JButton view;
    private Icon stopIcon;

    protected StopButtonController(DiscordBot discordBot, SongControlsPanel songControlsPanel) {
        model = discordBot;
        view = songControlsPanel.getStopButton();
        stopIcon = null;
    }

    @Override
    public void sync() {
        //I'm a static view for now.
    }

    @Override
    public void connect(API api) {
        Arrays.stream(view.getActionListeners()).toList().forEach(view::removeActionListener);
        view.addActionListener(l -> api.stop());
    }

    @Override
    public void loadIcons(Icons icon) {
        stopIcon = icon.getStopIcon();
        if (stopIcon == null) view.setText("STOP");
        else {
            view.setText("");
            view.setIcon(stopIcon);
        }
    }
}
