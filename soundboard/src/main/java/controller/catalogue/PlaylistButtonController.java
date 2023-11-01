package controller.catalogue;

import controller.API;
import model.catalogue.Playlist;
import view.catalogue.PlaylistButton;

import java.util.Arrays;

class PlaylistButtonController {

    private final Playlist playlist;
    private final PlaylistButton button;

    protected PlaylistButtonController(Playlist playlist, PlaylistButton button) {
        this.playlist = playlist;
        this.button = button;
    }

    public void connect(API api) {
        Arrays.stream(button.getActionListeners()).toList().forEach(button::removeActionListener);
        button.addActionListener(l -> api.play(playlist));
    }
}
