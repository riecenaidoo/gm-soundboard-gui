package editor.view;

import javax.swing.*;

public class SongPanel extends JPanel {

    private final JButton removeSong;

    protected SongPanel(String song) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(new JLabel(song));
        removeSong = new JButton("-");
        removeSong.setToolTipText("Remove Song from Playlist.");
    }
}
