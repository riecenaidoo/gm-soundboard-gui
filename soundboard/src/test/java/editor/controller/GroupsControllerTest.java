package editor.controller;

import editor.controller.group.GroupsController;
import editor.model.EditableCatalogue;
import editor.view.GroupsPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import soundboard.model.catalogue.Catalogue;
import soundboard.model.catalogue.Group;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupsControllerTest {

    Catalogue catalogue;
    EditableCatalogue model;
    GroupsPanel view;
    GroupsController groupsController;

    @BeforeEach
    void setUp() {
        catalogue = new Catalogue();
        model = new EditableCatalogue(catalogue);
        view = new GroupsPanel();
        groupsController = new GroupsController(view, model);
    }

    @Test
    void getSelectedGroup() {
        Group groupA = new Group("A");
        Group groupB = new Group("B");
        Group groupC = new Group("C");
        Group groupD = new Group("D");
        catalogue.add(groupA);
        catalogue.add(groupB);
        model.addGroup(groupC);
        model.addGroup(groupD);
        view.view(model);

        view.getGroupSelector().setSelectedIndex(0);
        assertEquals(Optional.empty(), groupsController.getSelectedGroup(),
                "First selection should not return a Group.");

        view.getGroupSelector().setSelectedIndex(1);
        assertEquals(Optional.of(groupA), groupsController.getSelectedGroup(),
                "Should retrieve from the first element of the original Catalogue.");

        view.getGroupSelector().setSelectedIndex(3);
        assertEquals(Optional.of(groupC), groupsController.getSelectedGroup(),
                "Should retrieve from the first element of the recentlyAdded list from the EditableCatalogue.");

//        view.getGroupSelector().setSelectedIndex(5); Selector prevents out of bounds selection.
    }

    @Nested
    class NoGroupSelected {

        @Test
        void removeGroupHidden() {
            throw new RuntimeException("TODO");
        }

        @Test
        void renameGroupHidden() {
            throw new RuntimeException("TODO");
        }

        @Test
        void addGroupVisible() {
            throw new RuntimeException("TODO");
        }
    }

    @Nested
    class RemoveGroup {

        @Test
        void cannotRenameRemovedGroup() {
            throw new RuntimeException("TODO");
        }

        @Test
        void cannotEditRemovedGroup() {
            throw new RuntimeException("TODO");
        }

        @Test
        void canUndoRemoveGroup() {
            throw new RuntimeException("TODO");
        }

        @Test
        void undoRemoveGroupEnablesEditGroup() {
            throw new RuntimeException("TODO");
        }

        @Test
        void undoRemoveGroupEnablesRenameGroup() {
            throw new RuntimeException("TODO");
        }
    }
}