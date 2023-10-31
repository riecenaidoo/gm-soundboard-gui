package controller.discordbot.songcontrols;

import controller.API;
import controller.discordbot.ServiceController;
import model.DiscordBot;
import model.Icons;
import view.discordbot.SongControlsPanel;

import javax.swing.*;

public class SkipController implements ServiceController {

    private final DiscordBot model; // I'll be used in the future.
    private final JButton view;
    private Icon skipIcon;

    public SkipController(DiscordBot discordBot, SongControlsPanel songControlsPanel) {
        model = discordBot;
        view = songControlsPanel.getSkipButton();
        skipIcon = null;
    }

    @Override
    public void sync() {
        //I'm a static view for now.
    }

    @Override
    public void connect(API api) {
        view.addActionListener(l -> api.skip());
    }

    @Override
    public void loadIcons(Icons icon) {
        skipIcon = icon.getSkipIcon();
        if (skipIcon == null) view.setText("->>");
        else {
            view.setText("");
            view.setIcon(skipIcon);
        }
    }
}
