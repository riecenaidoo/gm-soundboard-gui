package editor.controller;

import editor.view.CatalogueEditorView;
import editor.view.GroupsPanel;
import editor.view.PlaylistsPanel;
import soundboard.model.catalogue.Catalogue;
import soundboard.model.catalogue.Group;

/**
 * A CatalogueEditorController handles the input of the user on the editing panels
 * and updates the model, and the view.
 */
public class CatalogueEditorController {

    private final CatalogueEditorView view;
    private final Catalogue model;

    /**
     * Loads the model into the view, and registers action controllers for this view.
     *
     * @param view
     * @param model
     */
    public CatalogueEditorController(CatalogueEditorView view, Catalogue model) {
        this.view = view;
        this.model = model;
        view.getGroupsPanel().view(model);
        this.view.getGroupsPanel().getGroupSelector().addItemListener(e -> selectGroup());
        this.view.getPlaylistsPanel().getPlaylistSelector().addItemListener(e -> selectPlaylist());
    }

    /**
     * Selecting a Group will load its Playlists into the Playlist Panel
     * for editing.
     * <p>
     * Updates the View with the currently selected group in the GroupPanel's group selector.
     */
    public void selectGroup() {
        GroupsPanel groupsPanel = this.view.getGroupsPanel();
        int selectedIndex = groupsPanel.getGroupSelector().getSelectedIndex();
        if (selectedIndex == 0) {
            view.groupDeselected();
        } else {
            view.getPlaylistsPanel().view(model.get(selectedIndex - 1));  // See GroupsPanel#view
            view.groupSelected();
        }
    }

    public void selectPlaylist() {
        PlaylistsPanel playlistsPanel = this.view.getPlaylistsPanel();
        int selectedIndex = playlistsPanel.getPlaylistSelector().getSelectedIndex();
        if (selectedIndex == 0) {
            view.playlistDeselected();
        } else {
            Group group = model.get(this.view.getGroupsPanel().getGroupSelector().getSelectedIndex() - 1);
            view.getPlaylistEditorPanel().view(group.get(selectedIndex - 1)); // See PlaylistsPanel#view
            view.playlistSelected();
        }
    }
}
