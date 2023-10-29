package controller;

import model.Catalogue;
import view.CatalogueTabbedPane;
import view.CategoryPanel;

public class CatalogueController {
    public CatalogueController(API api, Catalogue catalogue, CatalogueTabbedPane view) {
        catalogue.getCategories().forEach(category -> {
            CategoryPanel categoryView = new CategoryPanel(category);
            new CategoryController(api, category, categoryView);
            view.addTab(category.getTitle(), categoryView);
        });
    }
}
