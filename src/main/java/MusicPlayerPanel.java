import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * MusicPlayer JPanel that provides song controls
 * such as volume increasing/decreasing, skipping, shuffling,
 * pausing, etc.
 */
public class MusicPlayerPanel extends JPanel {

    private final API api;

    private boolean isPlaying;
    private LoopMode loopMode;
    private boolean shufflePlay;

    /**
     * Builds a MusicPlayer JPanel that provides a graphical user interface for the API.
     * @param api the API this MusicPlayer is providing graphical controls for.
     */
    public MusicPlayerPanel(API api) {
        super();
        this.api = api;
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
        JButton pauseResume = new JButton("PAUSE");
        pauseResume.addActionListener(e -> {
            if (isPlaying) api.pause();
            else api.resume();

            isPlaying = !isPlaying;
            pauseResume.setText(isPlaying ? "PAUSE" : "RESUME");
        });

        return pauseResume;
    }

    private JButton buildStopPlay() {
        JButton stopPlay = new JButton("STOP");
        stopPlay.addActionListener(e -> api.stop());
        return stopPlay;
    }

    private JButton buildSkipBack() {
        JButton skipBack = new JButton("<<-");
        skipBack.addActionListener(e -> api.prev());
        return skipBack;
    }

    private JButton buildSkipAhead() {
        JButton skipAhead = new JButton("->>");
        skipAhead.addActionListener(e -> api.skip());
        return skipAhead;
    }

    private JButton buildShuffleToggle() {
        JButton shuffleToggle = new JButton("SHUFFLE: OFF");
        shuffleToggle.addActionListener(e -> {
            api.shuffle();
            shufflePlay = !shufflePlay;
            String labelText = (shufflePlay) ? "SHUFFLE: ON" : "SHUFFLE : OFF";
            shuffleToggle.setText(labelText);
        });
        return shuffleToggle;
    }

    private JButton buildLoopToggle() {
        JButton loopToggle = new JButton("LOOP: OFF");
        loopToggle.addActionListener(e -> {
            LoopMode[] modes = LoopMode.values();
            int nextModeIndex = ((loopMode.ordinal() + 1) % modes.length);
            LoopMode nextMode = modes[nextModeIndex];

            switch (loopMode) {
                case OFF -> {
                    api.loop_none();
                    loopToggle.setText("LOOP: " + nextMode);
                }
                case ALL -> {
                    api.loop();
                    loopToggle.setText("LOOP: " + nextMode);
                }
                case REPEAT -> {
                    api.repeat();
                    loopToggle.setText("LOOP: " + nextMode);
                }
            }

            loopMode = nextMode;
        });
        return loopToggle;
    }

    private enum LoopMode {
        OFF, ALL, REPEAT
    }
}
