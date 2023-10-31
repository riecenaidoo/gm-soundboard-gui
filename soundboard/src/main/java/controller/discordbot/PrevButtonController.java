package controller.discordbot;

import controller.API;
import model.DiscordBot;
import model.Icons;
import view.discordbot.SongControlsPanel;

import javax.swing.*;

class PrevButtonController implements DiscordBotController {

    private final DiscordBot model; // I'll be used in the future.
    private final JButton view;
    private Icon backIcon;

    protected PrevButtonController(DiscordBot discordBot, SongControlsPanel songControlsPanel) {
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
