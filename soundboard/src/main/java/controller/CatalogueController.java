package controller;

import model.Catalogue;
import view.CatalogueTabbedPane;
import view.CategoryPanel;

public class CatalogueController {

    private final Catalogue catalogue;
    private final CatalogueTabbedPane tabbedPane;

    public CatalogueController(Catalogue catalogue, CatalogueTabbedPane tabbedPane) {
        this.catalogue = catalogue;
        this.tabbedPane = tabbedPane;
    }

    public void loadCategories(API api) {
        catalogue.getCategories().forEach(category -> {
            CategoryPanel categoryView = new CategoryPanel(category);
            new CategoryController(category, categoryView).loadPlaylists(api);
            tabbedPane.addTab(category.getTitle(), categoryView);
        });
    }

    /**
     * Connects events to the API.
     *
     * @param api
     */
    public void connect(API api) {
    }
}
