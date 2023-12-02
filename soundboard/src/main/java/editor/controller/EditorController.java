package editor.controller;

import editor.view.EditorView;
import editor.view.GroupsPanel;
import editor.view.PlaylistsPanel;
import editor.view.SongStatusPanel;
import soundboard.model.catalogue.Catalogue;
import soundboard.model.catalogue.Group;
import soundboard.model.catalogue.Playlist;

import javax.swing.*;
import java.util.Optional;

/**
 * A CatalogueEditorController handles the input of the user on the editing panels
 * and updates the model, and the view.
 */
public class EditorController {

    private final EditorView view;
    private final Catalogue model;

    /**
     * If a reference to a JFrame was passed, changes to the CatalogueEditor's
     * view will invoke JFrame#pack to resize the window to accommodate new elements.
     */
    private final Optional<JFrame> app;

    /**
     * Loads the Catalogue Model into the EditorView, and registers action controllers
     * to handle events on the View. Updates to the View will dynamically resize the App's window.
     *
     * @param view initialised CatalogueEditor panel.
     * @param model initialised Catalogue to be displayed on the View.
     * @param app JFrame the CatalogueEditor panel belongs to.
     */
    public EditorController(EditorView view, Catalogue model, JFrame app) {
        this.view = view;
        this.model = model;
        this.app = Optional.ofNullable(app);
        view.getGroupsPanel().view(model);
        this.view.getGroupsPanel().getGroupSelector().addItemListener(e -> selectGroup());
        this.view.getPlaylistsPanel().getPlaylistSelector().addItemListener(e -> selectPlaylist());
    }

    /**
     * Loads the Catalogue Model into the EditorView, and registers action controllers
     * to handle events on the View.
     *
     * @param view  initialised CatalogueEditor panel.
     * @param model initialised Catalogue to be displayed on the View.
     */
    public EditorController(EditorView view, Catalogue model) {
        this(view, model, null);
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
     * for editing. Updates the View with the currently selected group in the GroupPanel's group selector.
     */
    public void selectGroup() {
        Optional<Group> selectedGroup = getSelectedGroup();
        if (selectedGroup.isEmpty()) {
            view.groupDeselected();
        } else {
            view.getPlaylistsPanel().view(selectedGroup.get());
            view.groupSelected();
        }

        if (app.isPresent()) app.get().pack();
    }

    /**
     * Selecting a Playlist will load its Songs into the PlaylistEditor Panel
     * for editing.Updates the View with the currently selected group in the PlaylistsPanel's group selector.
     */
    public void selectPlaylist() {
        Optional<Playlist> selectedPlaylist = getSelectedPlaylist();
        if (selectedPlaylist.isEmpty()) {
            view.playlistDeselected();
        } else {
            view.getPlaylistEditorPanel().getSongsView().removeAll();
            // TODO Correct this implementation.
            for (String song : selectedPlaylist.get()) {
                SongStatusPanel songStatusPanel = new SongStatusPanel(song);
                songStatusPanel.existingView();
                new SongStatusController(songStatusPanel);
                view.getPlaylistEditorPanel().addSongView(songStatusPanel);
            }

            view.playlistSelected();
        }

        if (app.isPresent()) app.get().pack();
    }
}
