package view.discordbot;

import javax.swing.*;

public class SongControlsPanel extends JPanel {

    private final JButton shuffleToggle;
    private final JButton loopToggle;
    private final JButton playToggle;

    private final JButton prevButton;
    private final JButton skipButton;
    private final JButton stopButton;


    public SongControlsPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        shuffleToggle = new JButton();          // Initial view unknown.
        loopToggle = new JButton();
        playToggle = new JButton();

        prevButton = new JButton("<-");     // Static views.
        skipButton = new JButton("->");
        stopButton = new JButton("STOP");

        this.add(shuffleToggle);                // In order of appearance, left to right.
        this.add(prevButton);
        this.add(playToggle);
        this.add(stopButton);
        this.add(skipButton);
        this.add(loopToggle);
    }

    public JButton getShuffleToggle() {
        return shuffleToggle;
    }

    public JButton getLoopToggle() {
        return loopToggle;
    }

    public JButton getPlayToggle() {
        return playToggle;
    }

    public JButton getPrevButton() {
        return prevButton;
    }

    public JButton getSkipButton() {
        return skipButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }
}
