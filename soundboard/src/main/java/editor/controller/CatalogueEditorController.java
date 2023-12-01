package editor.controller;

import editor.view.CatalogueEditorView;
import editor.view.GroupsPanel;
import editor.view.PlaylistsPanel;
import soundboard.model.catalogue.Catalogue;
import soundboard.model.catalogue.Group;
import soundboard.model.catalogue.Playlist;

import java.util.Optional;

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
     * @return Selected Group option from the Catalogue.
     */
    private Optional<Group> getSelectedGroup() {
        GroupsPanel groupsPanel = this.view.getGroupsPanel();
        int selectedIndex = (groupsPanel.getGroupSelector().getSelectedIndex()) - 1; // See GroupsPanel#view
        try {
            return Optional.of(model.get(selectedIndex));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    /**
     * @return Selected Playlist option from the selected Catalogue Group.
     */
    private Optional<Playlist> getSelectedPlaylist() {
        Optional<Group> selectedGroup = getSelectedGroup();
        if (selectedGroup.isEmpty()) return Optional.empty();

        Group group = selectedGroup.get();
        PlaylistsPanel playlistsPanel = this.view.getPlaylistsPanel();
        int selectedIndex = (playlistsPanel.getPlaylistSelector().getSelectedIndex()) - 1; // See PlaylistsPanel#view
        try {
            return Optional.of(group.get(selectedIndex));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }


    /**
     * Selecting a Group will load its Playlists into the Playlist Panel
     * for editing.
     * <p>
     * Updates the View with the currently selected group in the GroupPanel's group selector.
     */
    public void selectGroup() {
        Optional<Group> selectedGroup = getSelectedGroup();
        if (selectedGroup.isEmpty()) {
            view.groupDeselected();
        } else {
            view.getPlaylistsPanel().view(selectedGroup.get());
            view.groupSelected();
        }
    }

    /**
     * Selecting a Playlist will load its Songs into the PlaylistEditor Panel
     * for editing.
     * <p>
     * Updates the View with the currently selected group in the PlaylistsPanel's group selector.
     */
    public void selectPlaylist() {
        Optional<Playlist> selectedPlaylist = getSelectedPlaylist();
        if (selectedPlaylist.isEmpty()) {
            view.playlistDeselected();
        } else {
            view.getPlaylistEditorPanel().view(selectedPlaylist.get());
            view.playlistSelected();
        }
    }
}
