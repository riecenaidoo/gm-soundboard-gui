package controller;

import model.CatalogueGroup;
import view.CatalogueGroupPanel;
import view.PlaylistButton;

import java.util.ArrayList;
import java.util.Collection;

public class CatalogueGroupController {

    private final CatalogueGroup catalogueGroup;
    private final CatalogueGroupPanel view;
    private final Collection<PlaylistButtonController> controllers;

    public CatalogueGroupController(CatalogueGroup catalogueGroup, CatalogueGroupPanel view) {
        this.catalogueGroup = catalogueGroup;
        this.view = view;
        controllers = new ArrayList<>();
    }

    /**
     * Unload all elements from the Category view.
     */
    public void load() {
        if (!controllers.isEmpty()) unload();
        catalogueGroup.forEach(playlist -> {
            PlaylistButton button = new PlaylistButton(playlist);
            PlaylistButtonController controller = new PlaylistButtonController(playlist, button);
            controllers.add(controller);
            view.add(button);
        });
    }

    /**
     * Unload all elements from the Category view.
     */
    public void unload() {
        controllers.clear();
        view.removeAll();
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
