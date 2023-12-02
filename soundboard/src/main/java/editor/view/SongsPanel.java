package editor.view;

import soundboard.model.catalogue.Playlist;

import javax.swing.*;

/**
 * Contains a view of all Songs within a Playlist,
 * and input elements for receiving instructions to
 * edit the contents of that Playlist.
 */
public class SongsPanel extends JPanel {

    private final JTextField inputField;
    private final JButton submit;
    /**
     * View of all Songs within a Playlist. This Panel is used as a container
     * of individual SongViews.
     */
    private final JPanel songsView;

    protected SongsPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel songsInfo = new JPanel();
        songsInfo.add(new JLabel("SONGS"));
        this.add(songsInfo);

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
            SongStatusPanel songStatusPanel = new SongStatusPanel(song);
            songStatusPanel.existingView();
            addSongView(songStatusPanel);
        });
    }

    /**
     * Add a Song to this View.
     *
     * @param song SongView panel representing the Song.
     */
    public void addSongView(SongStatusPanel song) {
        songsView.add(song);
    }

    public JPanel getSongsView() {
        return songsView;
    }
}
