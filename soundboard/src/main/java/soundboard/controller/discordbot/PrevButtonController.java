package soundboard.controller.discordbot;

import soundboard.controller.RequestHandler;
import soundboard.model.DiscordBot;
import soundboard.model.Icons;
import soundboard.view.discordbot.SongControlsPanel;

import javax.swing.*;
import java.util.Arrays;

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
    public void connect(RequestHandler requestHandler) {
        Arrays.stream(view.getActionListeners()).toList().forEach(view::removeActionListener);
        view.addActionListener(l -> requestHandler.prev());
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
