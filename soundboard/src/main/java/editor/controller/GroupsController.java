package editor.controller;

import editor.model.EditableCatalogue;
import editor.view.AddGroupDialog;
import editor.view.EditGroupDialog;
import editor.view.GroupsPanel;
import soundboard.model.catalogue.Catalogue;
import soundboard.model.catalogue.Group;

import java.util.List;
import java.util.Optional;

public class GroupsController {

    private final GroupsPanel view;
    private final EditableCatalogue model;

    protected GroupsController(GroupsPanel view, EditableCatalogue model) {
        this.view = view;
        this.model = model;
        this.view.getAddGroup().addActionListener(e -> addGroup());
        this.view.getEditGroup().addActionListener(e -> editGroup());
    }

    /**
     * Retrieve the Group selected in GroupsPanel#GroupSelector from the model.
     * <br><br>
     * Retrieves via index. The initial element in the GroupSelector is always empty,
     * for no selection. The next list of elements are the existing Groups in the Catalogue,
     * followed by the list of Recently Added Groups in the EditableCatalogue.
     *
     * @return Group selected, if it exists.
     */
    public Optional<Group> getSelectedGroup() {
        int selectedIndex = view.getGroupSelector().getSelectedIndex() - 1;  // 1st element is empty
        if (selectedIndex < 0) return Optional.empty();

        Catalogue catalogue = model.getCatalogue();     // In original catalogue?
        if (selectedIndex < catalogue.size()) {
            return Optional.of(catalogue.get(selectedIndex));
        }

        selectedIndex -= catalogue.size();
        List<Group> recentlyAdded = model.getRecentlyAdded();      // In the recently added list?
        if (selectedIndex < recentlyAdded.size()) {
            return Optional.of(recentlyAdded.get(selectedIndex));
        } else {
            return Optional.empty();
        }
    }

    public void addGroup() {
        AddGroupDialog dialog = new AddGroupDialog();
        dialog.pack();
        dialog.setVisible(true);
    }

    public void editGroup() {
        EditGroupDialog dialog = new EditGroupDialog();
        dialog.pack();
        dialog.setVisible(true);
    }

    public void removeGroup() {

    }
}
