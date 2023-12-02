package editor.view;

import javax.swing.*;
import java.awt.*;

/**
 * View of a Song within a Playlist.
 * <br><br>
 * Indicates the status of the Song in the catalogue; existing,
 * marked for removal, recently added.
 */
public class SongStatusPanel extends JPanel {

    /**
     * This text field is not editable, and functions
     * as a label. Text fields elements allow mouse actions for highlighting and copying,
     * whereas label elements do not. Useful if a user wants to open URLs to test them.
     */
    private final JTextField songField;
    private final JButton editButton;

    public SongStatusPanel(String song) {
        super();
        this.setLayout((new BorderLayout()));
        editButton = new JButton();
        this.add(editButton, BorderLayout.LINE_START);
        songField = new JTextField(song);
        songField.setEditable(false);
        this.add(songField, BorderLayout.CENTER);
    }

    /**
     * Default View. Song already existing in the Catalogue.
     */
    public void existingView() {
        songField.setForeground(Color.BLACK);
        editButton.setText("-");
        editButton.setToolTipText("Remove Song from Playlist.");
    }

    /**
     * Song was recently added to the Catalogue.
     */
    public void recentlyAddedView() {
        songField.setForeground(Color.GREEN);
        editButton.setText("-");
        editButton.setToolTipText("Cancel adding Song to Playlist.");
    }

    /**
     * Song has been marked to be removed from the Catalogue.
     */
    public void markedForRemovalView() {
        songField.setForeground(Color.RED);
        editButton.setText("+");
        editButton.setToolTipText("Add Song back to Playlist.");
    }

    public JButton getEditButton() {
        return editButton;
    }
}
