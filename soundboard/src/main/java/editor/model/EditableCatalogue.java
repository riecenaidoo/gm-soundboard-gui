package editor.model;

import soundboard.model.catalogue.Catalogue;
import soundboard.model.catalogue.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private final List<EditableGroup> editedGroups;

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
        editedGroups = new ArrayList<>();
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

    public Optional<EditableGroup> getEditableGroup(Group group) {
        if (!(catalogue.contains(group) || recentlyAdded.contains(group))) {
            return Optional.empty();
        }

        for (EditableGroup editableGroup : editedGroups) {
            if (editableGroup.getGroup().equals(group)) {
                return Optional.of(editableGroup);
            }
        }

        EditableGroup editableGroup = new EditableGroup(group);
        editedGroups.add(editableGroup);
        return Optional.of(editableGroup);
    }

    public boolean isRecentlyAdded(Group group) {
        return recentlyAdded.contains(group);
    }

    public boolean isMarkedForRemoval(Group group) {
        return markedForRemoval.contains(group);
    }

    public boolean isGroupEdited(Group group) {
        return editedGroups.stream().anyMatch(editableGroup -> editableGroup.getGroup().equals(group));
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
