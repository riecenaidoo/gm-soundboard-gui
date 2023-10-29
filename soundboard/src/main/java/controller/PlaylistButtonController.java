package controller;

import model.Playlist;
import view.PlaylistButton;

import javax.swing.*;

public class PlaylistButtonController {

    private final API api;
    private final Playlist playlist;
    private final JButton button;


    public PlaylistButtonController(API api, Playlist playlist) {
        this.api = api;
        this.playlist = playlist;
        button = new PlaylistButton(playlist);
        button.addActionListener(l -> this.play());
    }

    public JButton getButton() {
        return button;
    }

    public void play() {
        api.play(playlist.getSongs());
    }
}
