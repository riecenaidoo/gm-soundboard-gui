package controller.discordbot;

import controller.API;
import model.DiscordBot;
import model.Icons;
import view.discordbot.SongControlsPanel;

import javax.swing.*;

/**
 * Controls the PlayPause Toggle Button in the Songs Control Panel.
 *
 * @see view.discordbot.SongControlsPanel
 */
class PlayToggleController implements DiscordBotController {

    private final DiscordBot model;
    private final JButton view;
    private Icon pauseIcon;
    private Icon playIcon;

    protected PlayToggleController(DiscordBot discordBot, SongControlsPanel songControlsPanel) {
        model = discordBot;
        view = songControlsPanel.getPlayToggle();
        pauseIcon = null;
        playIcon = null;
    }

    /**
     * Sets the view to show the DiscordBot is playing.
     * The action of PlayPause button will request playing be paused.
     */
    public void playingView() {
        if (pauseIcon == null) view.setText("PAUSE");
        else view.setIcon(pauseIcon);

    }

    /**
     * Sets the view to show the DiscordBot is paused.
     * The action of PlayPause button will request playing be resumed.
     */
    public void pausedView() {
        if (playIcon == null) view.setText("RESUME");
        else {
            view.setText("");
            view.setIcon(playIcon);
        }
    }

    @Override
    public void sync() {
        if (model.isPlaying()) playingView();
        else pausedView();
    }

    @Override
    public void connect(API api) {
        view.addActionListener(l -> {
            if (model.isPlaying()) api.pause();
            else api.resume();
            model.setPlaying(!model.isPlaying());
            sync();
        });
    }

    @Override
    public void loadIcons(Icons icons) {
        pauseIcon = icons.getPauseIcon();
        playIcon = icons.getPlayIcon();
    }
}
