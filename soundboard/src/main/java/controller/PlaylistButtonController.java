package controller;

import model.Playlist;
import view.PlaylistButton;

import javax.swing.*;

public class PlaylistButtonController {

    private final API api;
    private final Playlist playlist;
    private final JButton button;

    public PlaylistButtonController(API api, Playlist playlist, PlaylistButton button) {
        this.api = api;
        this.playlist = playlist;
        this.button = button;
        button.addActionListener(l -> this.play());
    }

    public void play() {
        api.play(playlist.getSongs());
    }
}
