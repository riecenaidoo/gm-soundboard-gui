package controller.discordbot.songcontrols;

import controller.API;
import controller.discordbot.ServiceController;
import model.DiscordBot;
import model.Icons;
import view.discordbot.SongControlsPanel;

import javax.swing.*;

public class SkipController implements ServiceController {

    private final JButton view;
    private Icon skipIcon;

    public SkipController(DiscordBot discordBot, SongControlsPanel songControlsPanel) {
        view = songControlsPanel.getButton("skip");
        skipIcon = null;
    }

    @Override
    public void sync() {
        if (skipIcon == null) view.setText("->>");
        else view.setIcon(skipIcon);
    }

    @Override
    public void connect(API api) {
        view.addActionListener(l -> api.skip());
    }

    @Override
    public void loadIcons(Icons icon) {
        skipIcon = icon.getSkipIcon();
    }
}
