package controller.catalogue;

import controller.API;
import model.catalogue.Playlist;
import view.catalogue.PlaylistButton;

public class PlaylistButtonController {

    private final Playlist playlist;
    private final PlaylistButton button;

    public PlaylistButtonController(Playlist playlist, PlaylistButton button) {
        this.playlist = playlist;
        this.button = button;
    }

    /**
     * Connects events to the API.
     *
     * @param api
     */
    public void connect(API api) {
        button.addActionListener(l -> api.play(playlist));
    }
}
