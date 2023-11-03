package soundboard.controller.discordbot;

import soundboard.controller.RequestHandler;
import soundboard.model.DiscordBot;
import soundboard.model.Icons;
import soundboard.view.discordbot.SongControlsPanel;

import javax.swing.*;
import java.util.Arrays;

/**
 * Controls the Shuffle Toggle Button in the Songs Control Panel.
 *
 * @see SongControlsPanel
 */
class ShuffleToggleController implements DiscordBotController {

    private final DiscordBot model;
    private final JButton view;
    private Icon shuffleIcon;
    private Icon shuffleOffIcon;

    protected ShuffleToggleController(DiscordBot discordBot, SongControlsPanel songControlsPanel) {
        model = discordBot;
        view = songControlsPanel.getShuffleToggle();
        shuffleIcon = null;
        shuffleOffIcon = null;
    }

    /**
     * Sets the view to show the DiscordBot is shuffling songs.
     * The action of PlayPause button will request songs not be shuffled.
     */
    public void shuffleOnView() {
        if (shuffleIcon == null) view.setText("SHUFFLE: ON");
        else {
            view.setText("");
            view.setIcon(shuffleIcon);
        }
    }

    /**
     * Sets the view to show the DiscordBot is not shuffling songs.
     * The action of PlayPause button will request songs be shuffled.
     */
    public void shuffleOffView() {
        if (shuffleOffIcon == null) view.setText("SHUFFLE: OFF");
        else {
            view.setText("");
            view.setIcon(shuffleOffIcon);
        }
    }

    @Override
    public void sync() {
        if (model.isShuffle()) shuffleOnView();
        else shuffleOffView();
    }

    @Override
    public void connect(RequestHandler requestHandler) {
        Arrays.stream(view.getActionListeners()).toList().forEach(view::removeActionListener);
        view.addActionListener(l -> {
            requestHandler.shuffle();  // Shuffle Toggles.
            model.setShuffle(!model.isShuffle());
            sync();
        });
    }

    @Override
    public void loadIcons(Icons icons) {
        shuffleIcon = icons.getShuffleIcon();
        shuffleOffIcon = icons.getShuffleOffIcon();
    }
}
