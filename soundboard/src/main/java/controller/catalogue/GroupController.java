package controller.catalogue;

import controller.API;
import model.catalogue.Group;
import view.catalogue.GroupPanel;
import view.catalogue.PlaylistButton;

import java.util.ArrayList;
import java.util.Collection;

public class GroupController {

    private final Group group;
    private final GroupPanel view;
    private final Collection<PlaylistButtonController> controllers;

    public GroupController(Group group, GroupPanel view) {
        this.group = group;
        this.view = view;
        controllers = new ArrayList<>();
    }

    /**
     * Unload all elements from the Category view.
     */
    public void load() {
        if (!controllers.isEmpty()) unload();
        group.forEach(playlist -> {
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
