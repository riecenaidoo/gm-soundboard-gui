package controller;

import model.Catalogue;
import view.CatalogueTabbedPane;
import view.CategoryPanel;

import java.util.ArrayList;
import java.util.Collection;

public class CatalogueController {

    private final Catalogue catalogue;
    private final CatalogueTabbedPane tabbedPane;
    private final Collection<CategoryController> controllers;

    public CatalogueController(Catalogue catalogue, CatalogueTabbedPane tabbedPane) {
        this.catalogue = catalogue;
        this.tabbedPane = tabbedPane;
        controllers = new ArrayList<>();
    }

    public void load() {
        catalogue.getCategories().forEach(category -> {
            CategoryPanel view = new CategoryPanel(category);
            CategoryController controller = new CategoryController(category, view);
            controller.load();
            controllers.add(controller);
            tabbedPane.addTab(category.getTitle(), view);
        });
    }

    /**
     * Connects events to the API.
     *
     * @param api
     */
    public void connect(API api) {
        controllers.forEach(controller -> controller.connect(api));
    }
}
