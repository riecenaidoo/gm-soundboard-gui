package view;

import model.Catalogue;

import javax.swing.*;

public class CatalogueTabbedPane extends JTabbedPane {
    public CatalogueTabbedPane(Catalogue catalogue) {
        super(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        catalogue.getCategories().forEach(category -> this.add(new CategoryPanel(category)));
    }
}
