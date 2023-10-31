package controller.discordbot;

import controller.API;
import model.DiscordBot;
import model.Icons;
import view.discordbot.SongControlsPanel;

import javax.swing.*;

/**
 * Controls the Shuffle Toggle Button in the Songs Control Panel.
 *
 * @see view.discordbot.SongControlsPanel
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
        else view.setIcon(shuffleIcon);

    }

    /**
     * Sets the view to show the DiscordBot is not shuffling songs.
     * The action of PlayPause button will request songs be shuffled.
     */
    public void shuffleOffView() {
        if (shuffleOffIcon == null) view.setText("SHUFFLE: OFF");
        else view.setIcon(shuffleOffIcon);
    }

    @Override
    public void sync() {
        if (model.isShuffle()) shuffleOnView();
        else shuffleOffView();
    }

    @Override
    public void connect(API api) {
        view.addActionListener(l -> {
            api.shuffle();  // Shuffle Toggles.
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
