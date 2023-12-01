package editor.view;

import soundboard.model.catalogue.Playlist;

import javax.swing.*;

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
        songsView.setLayout((new BoxLayout(songsView, BoxLayout.PAGE_AXIS)));
        this.add(new JScrollPane(songsView));
    }

    /**
     * Populate this View with Songs from a Playlist.
     *
     * @param model Playlist to view the Songs of.
     */
    public void view(Playlist model) {
        songsView.removeAll();
        model.forEach(song -> {
            SongView songView = new SongView(song);
            songView.existing();
            addSongView(songView);
        });
    }

    /**
     * Add a Song to this View.
     *
     * @param song SongView panel representing the Song.
     */
    public void addSongView(SongView song) {
        songsView.add(song);
    }
}
