package editor;

import soundboard.model.catalogue.Catalogue;

import javax.swing.*;

public class CatalogueEditor extends JPanel {

    private final Catalogue catalogue;
    private final GroupPanel groupPanel;

    public CatalogueEditor(Catalogue catalogue) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.catalogue = catalogue;
        groupPanel = new GroupPanel();
        this.add(groupPanel);
    }
}
