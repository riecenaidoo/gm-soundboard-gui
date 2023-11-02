package soundboard.view.catalogue;

import javax.swing.*;

public class CatalogueView extends JTabbedPane {
    public CatalogueView() {
        super(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        this.setToolTipText("Catalogue of Playlists to choose from.");
    }
}
