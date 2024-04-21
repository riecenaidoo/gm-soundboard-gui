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

    /**
     * Used to mark a Group for removal. If a Group has been marked for removal,
     * clicking the button again will undo the action.
     */
    private final JButton removeGroupToggle;

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

        addGroup = new JButton("+");
        addGroup.setToolTipText("Add a new Group to the Catalogue");
        groupsPanel.add(addGroup);

        groupSelector = new JComboBox<>();
        groupSelector.setToolTipText("Select a Group from the Catalogue");
        groupsPanel.add(groupSelector);

        removeGroupToggle = new JButton();
        groupsPanel.add(removeGroupToggle);
        removeGroupView();

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

    public JButton getRemoveGroupToggle() {
        return removeGroupToggle;
    }

    /**
     * Default View. Group was existing in the Catalogue.
     */
    public void groupExistingView() {
        groupSelector.setForeground(Color.BLACK);
        removeGroupView();
    }

    /**
     * Denote the currently selected Group was recently added.
     */
    public void groupRecentlyAddedView() {
        groupSelector.setForeground(Color.GREEN);
        removeGroupView();
    }

    /**
     * Denote the currently selected Group has been edited.
     */
    public void groupEditedView() {
        groupSelector.setForeground(Color.PINK);
        removeGroupView();
    }

    /**
     * Denote the currently selected Group has been marked for removal.
     */
    public void groupMarkedForRemovalView() {
        groupSelector.setForeground(Color.RED);
        undoRemoveGroupView();
    }

    /**
     * Denote that clicking the removeGroupToggle now will remove the selected Group.
     */
    private void removeGroupView(){
        removeGroupToggle.setText("-");
        removeGroupToggle.setToolTipText("Remove this Group from the Catalogue");
    }

    /**
     * Denote that clicking the removeGroupToggle now will undo the removal of the selected Group.
     */
    private void undoRemoveGroupView(){
        removeGroupToggle.setText("+");
        removeGroupToggle.setToolTipText("Undo removing this Group from the Catalogue");
    }
}
