package editor.view;

import soundboard.model.catalogue.Catalogue;

import javax.swing.*;

public class EditorView extends JPanel {

    private final Catalogue catalogue;
    private final GroupsPanel groupsPanel;
    private final PlaylistsPanel playlistsPanel;
    private final SongsPanel songsPanel;

    public EditorView(Catalogue catalogue) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.catalogue = catalogue;
        groupsPanel = new GroupsPanel();
        this.add(groupsPanel);
        playlistsPanel = new PlaylistsPanel();
        playlistsPanel.setVisible(false);
        this.add(playlistsPanel);
        songsPanel = new SongsPanel();
        songsPanel.setVisible(false);
        this.add(songsPanel);
    }

    public void groupSelected() {
        playlistsPanel.setVisible(true);
    }

    public void groupDeselected() {
        playlistsPanel.setVisible(false);
        songsPanel.setVisible(false);
    }

    public void playlistSelected() {
        songsPanel.setVisible(true);
    }

    public void playlistDeselected() {
        songsPanel.setVisible(false);
    }

    public GroupsPanel getGroupsPanel() {
        return groupsPanel;
    }

    public PlaylistsPanel getPlaylistsPanel() {
        return playlistsPanel;
    }

    public SongsPanel getSongsPanel() {
        return songsPanel;
    }
}
