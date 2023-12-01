package editor.view;

import soundboard.model.catalogue.Catalogue;

import javax.swing.*;

public class CatalogueEditorView extends JPanel {

    private final Catalogue catalogue;
    private final GroupsPanel groupsPanel;
    private final PlaylistsPanel playlistsPanel;
    private final PlaylistEditorPanel playlistEditorPanel;

    public CatalogueEditorView(Catalogue catalogue) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.catalogue = catalogue;
        groupsPanel = new GroupsPanel();
        this.add(groupsPanel);
        playlistsPanel = new PlaylistsPanel();
        playlistsPanel.setVisible(false);
        this.add(playlistsPanel);
        playlistEditorPanel = new PlaylistEditorPanel();
        playlistEditorPanel.setVisible(false);
        this.add(playlistEditorPanel);
    }

    public void groupSelected() {
        playlistsPanel.setVisible(true);
    }

    public void groupDeselected() {
        playlistsPanel.setVisible(false);
        playlistEditorPanel.setVisible(false);
    }

    public void playlistSelected() {
        playlistEditorPanel.setVisible(true);
    }

    public void playlistDeselected() {
        playlistEditorPanel.setVisible(false);
    }

    public GroupsPanel getGroupsPanel() {
        return groupsPanel;
    }

    public PlaylistsPanel getPlaylistsPanel() {
        return playlistsPanel;
    }

    public PlaylistEditorPanel getPlaylistEditorPanel() {
        return playlistEditorPanel;
    }
}
