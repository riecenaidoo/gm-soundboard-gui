package editor.controller;

import editor.view.CatalogueEditorView;
import editor.view.GroupsPanel;
import editor.view.PlaylistsPanel;
import soundboard.model.catalogue.Catalogue;

public class CatalogueEditorController {

    private final CatalogueEditorView view;
    private final Catalogue model;

    public CatalogueEditorController(CatalogueEditorView view, Catalogue model) {
        this.view = view;
        this.model = model;
        view.getGroupsPanel().view(model);
    }

    /**
     * Selecting a Group will load its Playlists into the Playlist Panel
     * for editing.
     */
    public void selectGroup() {
        GroupsPanel groupsPanel = this.view.getGroupsPanel();
        int selectedIndex = groupsPanel.getGroupSelector().getSelectedIndex();
        view.getPlaylistsPanel().view(model.get(selectedIndex));
        view.groupSelected();
    }

    public void selectPlaylist() {
        PlaylistsPanel playlistsPanel = this.view.getPlaylistsPanel();
        int selectedIndex = playlistsPanel.getPlaylistSelector().getSelectedIndex();
//        view.getPlaylistEditorPanel().view(model.get(selectedIndex));
        view.playlistSelected();
    }
}
