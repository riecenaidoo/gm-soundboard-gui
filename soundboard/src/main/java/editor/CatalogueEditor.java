package editor;

import soundboard.model.catalogue.Catalogue;

import javax.swing.*;

public class CatalogueEditor extends JPanel {

    Catalogue catalogue;
    JComboBox<String> groups;
    JButton addGroup;
    JButton editGroup;
    JButton removeGroup;

    public CatalogueEditor(Catalogue catalogue) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.catalogue = catalogue;

        JPanel groupsPanel = new JPanel();
        this.add(groupsPanel);
        groupsPanel.setLayout(new BoxLayout(groupsPanel, BoxLayout.LINE_AXIS));
        groups = new JComboBox<>();
        groupsPanel.add(groups);
        addGroup = new JButton("+");
        groupsPanel.add(addGroup);
        removeGroup = new JButton("-");
        groupsPanel.add(removeGroup);
        editGroup = new JButton("Edit");
        groupsPanel.add(editGroup);
    }
}
