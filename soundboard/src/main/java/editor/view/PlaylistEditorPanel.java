package editor.view;

import javax.swing.*;
import java.awt.*;

/**
 * Contains a view of all Songs within a Playlist,
 * and input elements for receiving instructions to
 * edit the contents of that Playlist.
 */
public class PlaylistEditorPanel extends JPanel {

    private final JTextField inputField;
    private final JButton submit;
    /**
     * View of all Songs within a Playlist. This Panel is used as a container
     * of individual SongViews.
     */
    private final JPanel songsView;

    protected PlaylistEditorPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel addSongView = new JPanel();
        addSongView.setLayout(new BoxLayout(addSongView, BoxLayout.LINE_AXIS));
        addSongView.add(new JLabel("Song URL: "));
        inputField = new JTextField();
        addSongView.add(inputField);
        submit = new JButton("Add");
        addSongView.add(submit);
        this.add(addSongView);

        songsView = new JPanel();
        songsView.setLayout(new GridLayout(0, 2));
        this.add(new JScrollPane(songsView));
    }

    /**
     * Add a Song to this View.
     *
     * @param song SongView panel representing the Song.
     */
    public void addSong(SongView song) {
        songsView.add(song);
    }
}
