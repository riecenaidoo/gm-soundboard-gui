package editor.view;

import soundboard.model.catalogue.Catalogue;

import javax.swing.*;

/**
 * Contains a view of all Groups within a Catalogue,
 * and input elements for receiving instructions to
 * edit a Group.
 */
public class GroupsPanel extends JPanel {

    JComboBox<String> groupSelector;
    JLabel currentGroup;
    JButton addGroup;
    JButton editGroup;
    JButton removeGroup;

    protected GroupsPanel() {
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

        editGroup = new JButton("Edit");
        editGroup.setToolTipText("Edit this Group");
        groupsPanel.add(editGroup);
    }

    /**
     * Populate this View with Groups from a Catalogue.
     *
     * @param model Catalogue to view the Groups of.
     */
    public void view(Catalogue model) {
        groupSelector.removeAll();
        model.forEach(group -> groupSelector.addItem(group.getName()));
    }
}
