package controller.catalogue;

import controller.API;
import model.catalogue.Catalogue;
import view.catalogue.CatalogueView;
import view.catalogue.GroupPanel;

import java.util.ArrayList;
import java.util.Collection;

public class CatalogueController {

    private final Catalogue catalogue;
    private final CatalogueView tabbedPane;
    private final Collection<GroupController> controllers;

    public CatalogueController(Catalogue catalogue, CatalogueView tabbedPane) {
        this.catalogue = catalogue;
        this.tabbedPane = tabbedPane;
        controllers = new ArrayList<>();
    }

    /**
     * Load all elements into the Catalogue view.
     */
    public void load() {
        if (!controllers.isEmpty()) unload();
        catalogue.forEach(category -> {
            GroupPanel view = new GroupPanel(category);
            GroupController controller = new GroupController(category, view);
            controller.load();
            controllers.add(controller);
            tabbedPane.addTab(category.getName(), view);
        });
    }

    /**
     * Unload all elements from the Catalogue view.
     */
    private void unload() {
        controllers.forEach(GroupController::unload);
        controllers.clear();
        tabbedPane.removeAll();
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
