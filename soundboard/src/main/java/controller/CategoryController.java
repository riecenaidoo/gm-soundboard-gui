package controller;

import model.Category;
import view.CategoryPanel;
import view.PlaylistButton;

public class CategoryController {
    public CategoryController(API api, Category category, CategoryPanel view) {
        category.getPlaylists().forEach(playlist -> {
            PlaylistButton button = new PlaylistButton(playlist);
            new PlaylistButtonController(api, playlist, button);
            view.add(button);
        });
    }
}
