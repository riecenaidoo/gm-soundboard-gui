package controller.discordbot.songcontrols;

import controller.API;
import controller.discordbot.ServiceController;
import model.DiscordBot;
import model.Icons;
import view.discordbot.SongControlsPanel;

import javax.swing.*;

public class StopController implements ServiceController {

    private final JButton view;
    private Icon stopIcon;

    public StopController(DiscordBot discordBot, SongControlsPanel songControlsPanel) {
        view = songControlsPanel.getButton("stop");
        stopIcon = null;
    }

    @Override
    public void sync() {
        if (stopIcon == null) view.setText("STOP");
        else view.setIcon(stopIcon);
    }

    @Override
    public void connect(API api) {
        view.addActionListener(l -> api.stop());
    }

    @Override
    public void loadIcons(Icons icon) {
        stopIcon = icon.getStopIcon();
    }
}
