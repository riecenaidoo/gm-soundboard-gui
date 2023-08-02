package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Singleton container class for the music miniplayer controller portion of the interface.
 * The miniplayer displays information on the song being played, provides a volume control,
 * skipping ahead/behind buttons, and a pause/resume button.
 */
public class MiniplayerPanel extends JPanel implements ActionListener {

    private static MiniplayerPanel INSTANCE;

    private final JLabel songTitle;
    private final JLabel playlist;
    private final JSlider volumeControl;
    private final JButton pauseResume;

    private boolean isPlaying;

    private MiniplayerPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        songTitle = new JLabel("Song Title");
        songTitle.setAlignmentX(CENTER_ALIGNMENT);
        this.add(songTitle);
        playlist = new JLabel("Playlist");
        this.add(playlist);
        playlist.setAlignmentX(CENTER_ALIGNMENT);
        volumeControl = new JSlider(0, 100);
        volumeControl.addChangeListener(e -> System.out.printf("[POST Request] Set the Bot's audio level '<%d>'.\n", volumeControl.getValue()));
        this.add(volumeControl);

        JPanel songControls = new JPanel();
        songControls.setLayout(new BoxLayout(songControls, BoxLayout.LINE_AXIS));
        JButton skipBack = new JButton("<<<");
        skipBack.addActionListener(e -> System.out.print("[POST Request] Skip to the previous song.\n"));
        songControls.add(skipBack);
        pauseResume = new JButton("PAUSE");
        pauseResume.setAlignmentX(CENTER_ALIGNMENT);
        pauseResume.addActionListener(this);
        songControls.add(pauseResume);
        JButton skipAhead = new JButton(">>>");
        skipAhead.addActionListener(e -> System.out.print("[POST Request] Skip to the next song.\n"));
        songControls.add(skipAhead);

        this.add(songControls);
        isPlaying = true;
    }

    public static MiniplayerPanel getMiniplayerPanel() {
        if (INSTANCE == null) INSTANCE = new MiniplayerPanel();
        return INSTANCE;
    }

    public void setSong(String songTitle) {
        this.songTitle.setText(songTitle);
    }

    public void setPlaylist(String playlist) {
        this.playlist.setText(playlist);
    }

    public void setVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            this.volumeControl.setValue(volume);
        } else {
            System.out.printf("[WARNING in MiniplayerPanel] Cannot set volume level to '%d'.\n", volume);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String requestInstruction = isPlaying ? "Pause the audio" : "Resume the audio";
        isPlaying = !isPlaying;
        String labelText = isPlaying ? "PAUSE" : "RESUME";
        pauseResume.setText(labelText);
        System.out.printf("[POST Request] %s.\n", requestInstruction);
    }
}
