package controller;

import model.Playlist;
import view.PlaylistButton;

public class PlaylistButtonController {

    private final Playlist playlist;
    private final PlaylistButton button;

    public PlaylistButtonController(Playlist playlist, PlaylistButton button) {

        this.playlist = playlist;
        this.button = button;
    }

    public void loadSongs(API api) {
        button.addActionListener(l -> api.play(playlist.getSongs()));
    }

    /**
     * Connects events to the API.
     *
     * @param api
     */
    public void connect(API api) {
    }
}
