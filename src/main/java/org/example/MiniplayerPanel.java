package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Singleton container class for the music miniplayer controller portion of the interface.
 * The miniplayer displays information on the song being played, provides a volume control,
 * skipping ahead/behind buttons, and a pause/resume button.
 */
public class MiniplayerPanel extends JPanel implements ActionListener {

    private final JLabel songTitle;
    private final JLabel playlist;
    private final JSlider volumeControl;
    private final JButton pauseResume;
    private final API api;

    private boolean isPlaying;

    public MiniplayerPanel(API api) {
        super();
        this.api = api;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        songTitle = new JLabel("Song Title");
        songTitle.setAlignmentX(CENTER_ALIGNMENT);
        this.add(songTitle);
        playlist = new JLabel("Playlist");
        this.add(playlist);
        playlist.setAlignmentX(CENTER_ALIGNMENT);
        volumeControl = new JSlider(0, 100);
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
        this.add(volumeControl);

        JPanel songControls = new JPanel();
        songControls.setLayout(new BoxLayout(songControls, BoxLayout.LINE_AXIS));
        JButton skipBack = new JButton("<<<");
        skipBack.addActionListener(e -> api.prev());
        songControls.add(skipBack);
        pauseResume = new JButton("PAUSE_");
        pauseResume.setAlignmentX(CENTER_ALIGNMENT);
        pauseResume.addActionListener(this);
        songControls.add(pauseResume);
        JButton skipAhead = new JButton(">>>");
        skipAhead.addActionListener(e -> api.skip());
        songControls.add(skipAhead);

        this.add(songControls);
        isPlaying = true;
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
        if (isPlaying) {
            api.pause();
        } else {
            api.resume();
        }

        isPlaying = !isPlaying;
        String labelText = isPlaying ? "PAUSE_" : "RESUME";
        pauseResume.setText(labelText);
    }
}
