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

    /**
     * This text field is not editable, and functions
     * as a label. Text fields elements allow mouse actions for highlighting and copying,
     * whereas label elements do not. Useful if a user wants to open URLs to test them.
     */
    private final JTextField url;
    private final JButton removeSong;

    protected SongView(String song) {
        super();
        this.setLayout((new BorderLayout()));
        removeSong = new JButton();
        this.add(removeSong, BorderLayout.LINE_START);
        url = new JTextField(song);
        url.setEditable(false);
        this.add(url, BorderLayout.CENTER);
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
