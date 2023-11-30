package editor.view;

import javax.swing.*;
import java.awt.*;

/**
 * View of a Song within a Playlist.
 * <br><br>
 * Indicates the status of the Song in the catalogue; existing,
 * marked for removal, recently added.
 */
public class SongView extends JPanel {

    private final JLabel url;
    private final JButton removeSong;

    protected SongView(String song) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        url = new JLabel(song);
        this.add(url);
        removeSong = new JButton();
    }

    /**
     * Default View. Song already existing in the Catalogue.
     */
    public void existing() {
        url.setForeground(Color.BLACK);
        removeSong.setText("-");
        removeSong.setToolTipText("Remove Song from Playlist.");
    }

    /**
     * Song was recently added to the Catalogue.
     */
    public void recentlyAdded() {
        url.setForeground(Color.GREEN);
        removeSong.setText("-");
        removeSong.setToolTipText("Cancel adding Song to Playlist.");
    }

    /**
     * Song has been marked to be removed from the Catalogue.
     */
    public void markedForRemoval() {
        url.setForeground(Color.RED);
        removeSong.setText("+");
        removeSong.setToolTipText("Add Song back to Playlist.");
    }
}
