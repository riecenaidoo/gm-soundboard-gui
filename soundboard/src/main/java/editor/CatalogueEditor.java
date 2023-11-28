package editor;

import soundboard.model.catalogue.Catalogue;

import javax.swing.*;

public class CatalogueEditor extends JPanel {

    Catalogue catalogue;
    JComboBox<String> groupSelector;
    JLabel currentGroup;
    JButton addGroup;
    JButton editGroup;
    JButton removeGroup;

    public CatalogueEditor(Catalogue catalogue) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.catalogue = catalogue;
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
}
