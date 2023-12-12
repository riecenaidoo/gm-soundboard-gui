package editor.model;

import soundboard.model.catalogue.Catalogue;
import soundboard.model.catalogue.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Editable Catalogue that can track changes (edits), without mutating or cloning
 * the original Catalogue directly. Allows changes to be rolled back safely.
 *
 * @see Catalogue
 */
public class EditableCatalogue {

    private final Catalogue catalogue;
    private final List<Group> markedForRemoval;
    private final List<Group> recentlyAdded;

    /**
     * Wraps a Catalogue, creating an Editable Catalogue that can tracks changes
     * to the original, without mutating it.
     *
     * @param catalogue mutable Catalogue to track changes for.
     */
    public EditableCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
        markedForRemoval = new ArrayList<>();
        recentlyAdded = new ArrayList<>();
    }

    public void addGroup(Group group) {
        recentlyAdded.add(group);
    }

    public void undoAddGroup(Group group) {
        recentlyAdded.remove(group);
    }

    public void removeGroup(Group group) {
        markedForRemoval.add(group);
    }

    public void undoRemoveGroup(Group group) {
        markedForRemoval.remove(group);
    }

    public boolean isRecentlyAdded(Group group) {
        return recentlyAdded.contains(group);
    }

    public boolean isMarkedForRemoval(Group group) {
        return markedForRemoval.contains(group);
    }

    /**
     * Commits changes to the Catalogue. This action is final, and will mutate the original
     * Catalogue.
     */
    public void saveChanges() {
        markedForRemoval.forEach(catalogue::remove);
        catalogue.addAll(recentlyAdded);
        clearChanges();
    }

    public void clearChanges() {
        markedForRemoval.clear();
        recentlyAdded.clear();
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public List<Group> getRecentlyAdded() {
        return recentlyAdded;
    }
}
