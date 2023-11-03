package soundboard.controller.catalogue;

import soundboard.controller.RequestHandler;
import soundboard.model.catalogue.Group;
import soundboard.view.catalogue.GroupPanel;
import soundboard.view.catalogue.PlaylistButton;

import java.util.ArrayList;
import java.util.Collection;

class GroupController {

    private final Group group;
    private final GroupPanel view;
    private final Collection<PlaylistButtonController> controllers;

    protected GroupController(Group group, GroupPanel view) {
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

    public void connect(RequestHandler requestHandler) {
        controllers.forEach(controller -> controller.connect(requestHandler));
    }
}
