package editor.view;

import javax.swing.*;

/**
 * Contains a view of all Playlists within a Catalogue,
 * and input elements for receiving instructions to
 * edit a Playlist.
 */
public class PlaylistsPanel extends JPanel {

    JComboBox<String> playlistSelector;
    JLabel currentPlaylist;
    JButton addPlaylist;
    JButton editPlaylist;
    JButton removePlaylist;

    protected PlaylistsPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel playlistInfoPanel = new JPanel();
        this.add(playlistInfoPanel);
        JPanel playlistEditingPanel = new JPanel();
        playlistEditingPanel.setLayout(new BoxLayout(playlistEditingPanel, BoxLayout.LINE_AXIS));
        this.add(playlistEditingPanel);

        currentPlaylist = new JLabel("PLAYLIST");
        playlistInfoPanel.add(currentPlaylist);

        playlistSelector = new JComboBox<>();
        playlistSelector.setToolTipText("Select a Playlist from the Catalogue");
        playlistEditingPanel.add(playlistSelector);

        addPlaylist = new JButton("+");
        addPlaylist.setToolTipText("Add a new Playlist to the Catalogue");
        playlistEditingPanel.add(addPlaylist);

        removePlaylist = new JButton("-");
        removePlaylist.setToolTipText("Remove this Playlist from the Catalogue");
        playlistEditingPanel.add(removePlaylist);

        editPlaylist = new JButton("Edit");
        editPlaylist.setToolTipText("Edit this Playlist");
        playlistEditingPanel.add(editPlaylist);
    }
}
