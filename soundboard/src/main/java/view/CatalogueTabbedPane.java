package view;

import javax.swing.*;

public class CatalogueTabbedPane extends JTabbedPane {
    public CatalogueTabbedPane() {
        super(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        this.setToolTipText("Catalogue of Playlists to choose from.");
    }
}
