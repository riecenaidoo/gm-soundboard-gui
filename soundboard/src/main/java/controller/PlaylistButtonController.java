package controller;

import model.Playlist;
import view.PlaylistButton;

public class PlaylistButtonController {
    public PlaylistButtonController(API api, Playlist playlist, PlaylistButton button) {
        button.addActionListener(l -> api.play(playlist.getSongs()));
    }
}
