package controller;

import model.Category;
import view.CategoryPanel;
import view.PlaylistButton;

public class CategoryController {
    private final Category category;
    private final CategoryPanel view;

    public CategoryController(Category category, CategoryPanel view) {
        this.category = category;
        this.view = view;
    }

    public void loadPlaylists(API api) {
        category.getPlaylists().forEach(playlist -> {
            PlaylistButton button = new PlaylistButton(playlist);
            new PlaylistButtonController(playlist, button).loadSongs(api);
            view.add(button);
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
