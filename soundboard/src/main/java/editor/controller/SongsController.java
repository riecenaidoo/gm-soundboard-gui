package editor.controller;

import editor.view.SongsPanel;
import soundboard.model.catalogue.Playlist;

public class SongsController {

    private final SongsPanel view;
    private final Playlist model;

    public SongsController(SongsPanel view, Playlist model) {
        this.view = view;
        this.model = model;
        this.view.getSubmitButton().addActionListener(e -> addSong());
    }

    private void addSong() {
        // TODO
    }
}
