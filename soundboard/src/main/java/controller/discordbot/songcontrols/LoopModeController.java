package controller.discordbot.songcontrols;

import controller.API;
import controller.discordbot.ServiceController;
import model.DiscordBot;
import model.Icons;
import view.discordbot.SongControlsPanel;

import javax.swing.*;

/**
 * Controls the Loop Mode Toggle Button, that toggles between the
 * Discord Bot's three looping modes.
 *
 * @see model.DiscordBot.LoopMode
 * @see view.discordbot.SongControlsPanel
 */
public class LoopModeController implements ServiceController {

    private final DiscordBot model;
    private final JButton view;

    private Icon loopOffIcon;
    private Icon loopOnIcon;
    private Icon repeatOneIcon;

    public LoopModeController(DiscordBot discordBot, SongControlsPanel songControlsPanel) {
        this.model = discordBot;
        this.view = songControlsPanel.getButton("loopMode");
        loopOffIcon = null;
        loopOnIcon = null;
        repeatOneIcon = null;
    }

    public void loopOffView() {
        if (loopOffIcon == null) view.setText("LOOP: " + model.getLoopMode());
        else view.setIcon(loopOffIcon);
    }

    public void loopOnView() {
        if (loopOnIcon == null) view.setText("LOOP: " + model.getLoopMode());
        else view.setIcon(loopOnIcon);
    }

    public void loopOneView() {
        if (repeatOneIcon == null) view.setText("LOOP: " + model.getLoopMode());
        else view.setIcon(repeatOneIcon);
    }
    
    @Override
    public void sync() {
        switch (model.getLoopMode()) {
            case OFF -> loopOffView();
            case ON -> loopOnView();
            case ONE -> loopOneView();
        }
    }

    @Override
    public void connect(API api) {
        view.addActionListener(l -> {
            DiscordBot.LoopMode[] modes = DiscordBot.LoopMode.values();
            int nextModeIndex = ((model.getLoopMode().ordinal() + 1) % modes.length);
            model.setLoopMode(modes[nextModeIndex]);    // Toggle Between Modes.
            switch (model.getLoopMode()) {
                case OFF -> api.loop_none();
                case ON -> api.loop();
                case ONE -> api.repeat();
            }
            sync();
        });
    }

    @Override
    public void loadIcons(Icons icons) {
        loopOffIcon = icons.getLoopOffIcon();
        loopOnIcon = icons.getLoopIcon();
        repeatOneIcon = icons.getRepeatOneIcon();
    }
}
