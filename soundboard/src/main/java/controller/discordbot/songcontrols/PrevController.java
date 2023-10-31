package controller.discordbot.songcontrols;

import controller.API;
import controller.discordbot.ServiceController;
import model.DiscordBot;
import model.Icons;
import view.discordbot.SongControlsPanel;

import javax.swing.*;

public class PrevController implements ServiceController {

    private final JButton view;
    private Icon backIcon;

    public PrevController(DiscordBot discordBot, SongControlsPanel songControlsPanel) {
        view = songControlsPanel.getButton("prev");
        backIcon = null;
    }

    @Override
    public void sync() {
        if (backIcon == null) view.setText("<<-");
        else view.setIcon(backIcon);
    }

    @Override
    public void connect(API api) {
        view.addActionListener(l -> api.prev());
    }

    @Override
    public void loadIcons(Icons icon) {
        backIcon = icon.getBackIcon();
    }
}
