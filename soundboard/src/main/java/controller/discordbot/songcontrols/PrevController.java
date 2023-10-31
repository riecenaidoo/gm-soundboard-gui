package controller.discordbot.songcontrols;

import controller.API;
import controller.discordbot.ServiceController;
import model.DiscordBot;
import model.Icons;
import view.discordbot.SongControlsPanel;

import javax.swing.*;

public class PrevController implements ServiceController {

    private final DiscordBot model; // I'll be used in the future.
    private final JButton view;
    private Icon backIcon;

    public PrevController(DiscordBot discordBot, SongControlsPanel songControlsPanel) {
        model = discordBot;
        view = songControlsPanel.getPrevButton();
        backIcon = null;
    }

    @Override
    public void sync() {
        //I'm a static view for now.
    }

    @Override
    public void connect(API api) {
        view.addActionListener(l -> api.prev());
    }

    @Override
    public void loadIcons(Icons icon) {
        backIcon = icon.getBackIcon();
        if (backIcon == null) view.setText("<<-");
        else {
            view.setText("");
            view.setIcon(backIcon);
        }
    }
}
