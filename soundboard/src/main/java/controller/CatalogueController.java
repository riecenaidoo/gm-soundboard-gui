package controller;

import model.Catalogue;
import view.CatalogueTabbedPane;
import view.CategoryPanel;
import view.PlaylistButton;

public class CatalogueController {
    public CatalogueController(API api, Catalogue catalogue, CatalogueTabbedPane view) {
        catalogue.getCategories().forEach(category -> {
            CategoryPanel categoryView = new CategoryPanel(category);

            category.getPlaylists().forEach(playlist -> {
                PlaylistButton button = new PlaylistButton(playlist);
                new PlaylistButtonController(api, playlist, button);
                categoryView.add(button);
            });

            view.addTab(category.getTitle(), categoryView);
        });
    }
}
