package controller;

import model.Category;
import view.CategoryPanel;
import view.PlaylistButton;

import java.util.ArrayList;
import java.util.Collection;

public class CategoryController {

    private final Category category;
    private final CategoryPanel view;
    private final Collection<PlaylistButtonController> controllers;

    public CategoryController(Category category, CategoryPanel view) {
        this.category = category;
        this.view = view;
        controllers = new ArrayList<>();
    }

    /**
     * Unload all elements from the Category view.
     */
    public void load() {
        if (!controllers.isEmpty()) unload();
        category.getPlaylists().forEach(playlist -> {
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
