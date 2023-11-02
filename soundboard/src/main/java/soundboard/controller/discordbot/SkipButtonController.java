package soundboard.controller.discordbot;

import soundboard.controller.RequestHandler;
import soundboard.model.DiscordBot;
import soundboard.model.Icons;
import soundboard.view.discordbot.SongControlsPanel;

import javax.swing.*;
import java.util.Arrays;

class SkipButtonController implements DiscordBotController {

    private final DiscordBot model; // I'll be used in the future.
    private final JButton view;
    private Icon skipIcon;

    protected SkipButtonController(DiscordBot discordBot, SongControlsPanel songControlsPanel) {
        model = discordBot;
        view = songControlsPanel.getSkipButton();
        skipIcon = null;
    }

    @Override
    public void sync() {
        //I'm a static view for now.
    }

    @Override
    public void connect(RequestHandler requestHandler) {
        Arrays.stream(view.getActionListeners()).toList().forEach(view::removeActionListener);
        view.addActionListener(l -> requestHandler.skip());
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
