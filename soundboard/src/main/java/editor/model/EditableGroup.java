package editor.model;

import soundboard.model.catalogue.Group;
import soundboard.model.catalogue.Playlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Editable Group that can track changes (edits), without mutating or cloning
 * the original Group directly. Allows changes to be rolled back safely.
 *
 * @see Group
 */
public class EditableGroup {

    private final Group group;
    private final List<Playlist> markedForRemoval;
    private final List<Playlist> recentlyAdded;
    private String updatedName;

    /**
     * Wraps a Group, creating an Editable Group that can tracks changes
     * to the original, without mutating it.
     *
     * @param group mutable Group to track changes for.
     */
    public EditableGroup(Group group) {
        this.group = group;
        markedForRemoval = new ArrayList<>();
        recentlyAdded = new ArrayList<>();
        updatedName = group.getName();
    }

    public void addPlaylist(Playlist playlist) {
        recentlyAdded.add(playlist);
    }

    public void undoAddPlaylist(Playlist playlist) {
        recentlyAdded.remove(playlist);
    }

    public void removePlaylist(Playlist playlist) {
        markedForRemoval.add(playlist);
    }

    public void undoRemovePlaylist(Playlist playlist) {
        markedForRemoval.remove(playlist);
    }

    public void updateTitle(String title) {
        updatedName = title;
    }

    public boolean isRecentlyAdded(Playlist playlist) {
        return recentlyAdded.contains(playlist);
    }

    public boolean isMarkedForRemoval(Playlist playlist) {
        return markedForRemoval.contains(playlist);
    }

    /**
     * Commits changes to the Group. This action is final, and will mutate the original
     * Group.
     */
    public void saveChanges() {
        markedForRemoval.forEach(group::remove);
        group.addAll(recentlyAdded);
        if (!updatedName.isBlank()) group.setName(updatedName);
        clearChanges();
    }

    public void clearChanges() {
        markedForRemoval.clear();
        recentlyAdded.clear();
        updatedName = group.getName();
    }

    /**
     * Accessor to the original wrapped Group.
     *
     * @return the original Group.
     */
    public Group getGroup() {
        return group;
    }
}