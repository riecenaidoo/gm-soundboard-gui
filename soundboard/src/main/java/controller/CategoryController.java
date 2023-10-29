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

    public void load() {
        category.getPlaylists().forEach(playlist -> {
            PlaylistButton button = new PlaylistButton(playlist);
            PlaylistButtonController controller = new PlaylistButtonController(playlist, button);
            controllers.add(controller);
            view.add(button);
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
