package soundboard.controller.discordbot;

import soundboard.controller.RequestHandler;
import soundboard.model.DiscordBot;
import soundboard.model.Icons;
import soundboard.view.discordbot.SongControlsPanel;

import javax.swing.*;
import java.util.Arrays;

/**
 * Controls the Loop Mode Toggle Button, that toggles between the
 * Discord Bot's three looping modes.
 *
 * @see DiscordBot.LoopMode
 * @see SongControlsPanel
 */
class LoopToggleController implements DiscordBotController {

    private final DiscordBot model;
    private final JButton view;

    private Icon loopOffIcon;
    private Icon loopOnIcon;
    private Icon repeatOneIcon;

    protected LoopToggleController(DiscordBot discordBot, SongControlsPanel songControlsPanel) {
        this.model = discordBot;
        this.view = songControlsPanel.getLoopToggle();
        loopOffIcon = null;
        loopOnIcon = null;
        repeatOneIcon = null;
    }

    public void loopOffView() {
        if (loopOffIcon == null) view.setText("LOOP: " + model.getLoopMode());
        else {
            view.setText("");
            view.setIcon(loopOffIcon);
        }
    }

    public void loopOnView() {
        if (loopOnIcon == null) view.setText("LOOP: " + model.getLoopMode());
        else {
            view.setText("");
            view.setIcon(loopOnIcon);
        }
    }

    public void loopOneView() {
        if (repeatOneIcon == null) view.setText("LOOP: " + model.getLoopMode());
        else {
            view.setText("");
            view.setIcon(repeatOneIcon);
        }
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
    public void connect(RequestHandler requestHandler) {
        Arrays.stream(view.getActionListeners()).toList().forEach(view::removeActionListener);
        view.addActionListener(l -> {
            DiscordBot.LoopMode[] modes = DiscordBot.LoopMode.values();
            int nextModeIndex = ((model.getLoopMode().ordinal() + 1) % modes.length);
            model.setLoopMode(modes[nextModeIndex]);    // Toggle Between Modes.
            switch (model.getLoopMode()) {
                case OFF -> requestHandler.loop_none();
                case ON -> requestHandler.loop();
                case ONE -> requestHandler.repeat();
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
