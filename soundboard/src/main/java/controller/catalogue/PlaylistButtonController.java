package controller.catalogue;

import controller.API;
import model.catalogue.Playlist;
import view.catalogue.PlaylistButton;

class PlaylistButtonController {

    private final Playlist playlist;
    private final PlaylistButton button;

    protected PlaylistButtonController(Playlist playlist, PlaylistButton button) {
        this.playlist = playlist;
        this.button = button;
    }

    public void connect(API api) {
        button.addActionListener(l -> api.play(playlist));
    }
}
