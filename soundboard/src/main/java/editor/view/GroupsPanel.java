package editor.view;

import editor.model.EditableCatalogue;

import javax.swing.*;
import java.awt.*;

/**
 * Contains a view of all Groups within a Catalogue,
 * and input elements for receiving instructions to
 * edit a Group.
 */
public class GroupsPanel extends JPanel {

    private final JComboBox<String> groupSelector;
    private final JLabel currentGroup;
    private final JButton addGroup;
    private final JButton renameGroup;
    private final JButton removeGroup;

    public GroupsPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel groupInfoPanel = new JPanel();
        this.add(groupInfoPanel);
        JPanel groupsPanel = new JPanel();
        groupsPanel.setLayout(new BoxLayout(groupsPanel, BoxLayout.LINE_AXIS));
        this.add(groupsPanel);

        currentGroup = new JLabel("GROUP");
        groupInfoPanel.add(currentGroup);

        groupSelector = new JComboBox<>();
        groupSelector.setToolTipText("Select a Group from the Catalogue");
        groupsPanel.add(groupSelector);

        addGroup = new JButton("+");
        addGroup.setToolTipText("Add a new Group to the Catalogue");
        groupsPanel.add(addGroup);

        removeGroup = new JButton("-");
        removeGroup.setToolTipText("Remove this Group from the Catalogue");
        groupsPanel.add(removeGroup);

        renameGroup = new JButton("Rename");
        renameGroup.setToolTipText("Rename this Group");
        groupsPanel.add(renameGroup);
    }

    /**
     * Populate this View with Groups from an EditableCatalogue.
     * <br><br>
     * The first item in the combo box selector is empty,
     * for no selection. The next list of items are the original
     * elements of the wrapped Catalogue, followed by the
     * Recently Added Groups of the EditableCatalogue.
     *
     * @param model Catalogue to view the Groups of.
     */
    public void view(EditableCatalogue model) {
        groupSelector.removeAllItems();
        groupSelector.addItem("");
        model.getCatalogue().forEach(group -> groupSelector.addItem(group.getName()));
        model.getRecentlyAdded().forEach(group -> groupSelector.addItem(group.getName()));
    }

    public JComboBox<String> getGroupSelector() {
        return groupSelector;
    }

    public JButton getAddGroup() {
        return addGroup;
    }

    public JButton getRenameGroup() {
        return renameGroup;
    }

    public JButton getRemoveGroup() {
        return removeGroup;
    }

    /**
     * Default View. Group was existing in the Catalogue.
     */
    public void groupExistingView() {
        groupSelector.setForeground(Color.BLACK);
    }

    /**
     * Denote the currently selected Group was recently added.
     */
    public void groupRecentlyAddedView() {
        groupSelector.setForeground(Color.GREEN);
    }

    /**
     * Denote the currently selected Group has been edited.
     */
    public void groupEditedView() {
        groupSelector.setForeground(Color.PINK);
    }

    /**
     * Denote the currently selected Group has been marked for removal.
     */
    public void groupMarkedForRemovalView() {
        groupSelector.setForeground(Color.RED);
    }
}
