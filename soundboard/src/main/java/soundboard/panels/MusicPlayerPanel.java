package soundboard.panels;

import soundboard.API;
import soundboard.Icons;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Music Player JPanel that provides song controls
 * such as volume increasing/decreasing, skipping, shuffling,
 * pausing, etc.
 */
public class MusicPlayerPanel extends JPanel {

    private final API api;
    private final Icons icons;

    private boolean isPlaying;
    private LoopMode loopMode;
    private boolean shufflePlay;

    /**
     * Builds a MusicPlayer JPanel that provides a graphical user interface for the soundboard API.
     *
     * @param api   the API this MusicPlayer is providing graphical controls for.
     * @param icons available Icons of the Soundboard.
     */
    public MusicPlayerPanel(API api, Icons icons) {
        super();
        this.api = api;
        this.icons = icons;
        this.loopMode = LoopMode.OFF;
        this.shufflePlay = false;
        this.isPlaying = true;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(buildVolumeControl());
        this.add(buildSongControls());
    }

    private JSlider buildVolumeControl() {
        JSlider volumeControl = new JSlider(0, 100);
        volumeControl.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                api.set_volume(volumeControl.getValue());
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        return volumeControl;
    }

    private JPanel buildSongControls() {
        JPanel songControls = new JPanel();
        songControls.setLayout(new BoxLayout(songControls, BoxLayout.LINE_AXIS));

        songControls.add(buildShuffleToggle());
        songControls.add(buildSkipBack());
        songControls.add(buildPauseResume());
        songControls.add(buildStopPlay());
        songControls.add(buildSkipAhead());
        songControls.add(buildLoopToggle());

        return songControls;
    }

    private JButton buildPauseResume() {
        JButton pauseResume = new JButton();
        Icon pauseIcon = icons.getPauseIcon();
        Icon playIcon = icons.getPlayIcon();

        if (pauseIcon == null) pauseResume.setText("PAUSE");
        else pauseResume.setIcon(pauseIcon);

        pauseResume.addActionListener(e -> {
            if (isPlaying) api.pause();
            else api.resume();

            isPlaying = !isPlaying;

            if (isPlaying) {
                if (pauseIcon == null) pauseResume.setText("PAUSE");
                else pauseResume.setIcon(pauseIcon);
            } else {
                if (playIcon == null) pauseResume.setText("RESUME");
                else pauseResume.setIcon(playIcon);
            }
        });

        return pauseResume;
    }

    private JButton buildStopPlay() {
        JButton stopPlay = new JButton();

        Icon stopIcon = icons.getStopIcon();
        if (stopIcon == null) stopPlay.setText("STOP");
        else {
            stopPlay.setIcon(stopIcon);
        }

        stopPlay.addActionListener(e -> api.stop());
        return stopPlay;
    }

    private JButton buildSkipBack() {
        JButton skipBack = new JButton();

        Icon backIcon = icons.getBackIcon();
        if (backIcon == null) skipBack.setText("<<-");
        else {
            skipBack.setIcon(backIcon);
        }

        skipBack.addActionListener(e -> api.prev());
        return skipBack;
    }

    private JButton buildSkipAhead() {
        JButton skipAhead = new JButton();

        Icon skipIcon = icons.getSkipIcon();
        if (skipIcon == null) skipAhead.setText("->>");
        else {
            skipAhead.setIcon(skipIcon);
        }

        skipAhead.addActionListener(e -> api.skip());
        return skipAhead;
    }

    private JButton buildShuffleToggle() {
        JButton shuffleToggle = new JButton();

        Icon shuffleIcon = icons.getShuffleIcon();
        Icon shuffleOffIcon = icons.getShuffleOffIcon();

        if (shuffleOffIcon == null) shuffleToggle.setText("SHUFFLE: OFF");
        else shuffleToggle.setIcon(shuffleOffIcon);

        shuffleToggle.addActionListener(e -> {
            api.shuffle();
            shufflePlay = !shufflePlay;

            if (shufflePlay) {
                if (shuffleIcon == null) shuffleToggle.setText("SHUFFLE: ON");
                else shuffleToggle.setIcon(shuffleIcon);
            } else {
                if (shuffleOffIcon == null) shuffleToggle.setText("SHUFFLE: OFF");
                else shuffleToggle.setIcon(shuffleOffIcon);
            }
        });

        return shuffleToggle;
    }

    private JButton buildLoopToggle() {
        JButton loopToggle = new JButton();

        Icon loopIcon = icons.getLoopIcon();
        Icon loopOffIcon = icons.getLoopOffIcon();
        Icon repeatOneIcon = icons.getRepeatOneIcon();

        if (loopOffIcon == null) loopToggle.setText("LOOP: OFF");
        else loopToggle.setIcon(loopOffIcon);

        loopToggle.addActionListener(e -> {
            LoopMode[] modes = LoopMode.values();
            int nextModeIndex = ((loopMode.ordinal() + 1) % modes.length);
            LoopMode nextMode = modes[nextModeIndex];
            loopMode = nextMode;

            switch (loopMode) {
                case OFF -> {
                    api.loop_none();
                    if (loopOffIcon == null) loopToggle.setText("LOOP: " + nextMode);
                    else loopToggle.setIcon(loopOffIcon);
                }
                case ALL -> {
                    api.loop();
                    if (loopIcon == null) loopToggle.setText("LOOP: " + nextMode);
                    else loopToggle.setIcon(loopIcon);
                }
                case REPEAT -> {
                    api.repeat();
                    if (repeatOneIcon == null) loopToggle.setText("LOOP: " + nextMode);
                    else loopToggle.setIcon(repeatOneIcon);
                }
            }
        });

        return loopToggle;
    }

    private enum LoopMode {
        OFF, ALL, REPEAT
    }
}
