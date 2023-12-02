package editor.controller;

import editor.view.SongStatusPanel;
import editor.view.SongsPanel;
import soundboard.model.catalogue.Playlist;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class SongsController {

    private final SongsPanel view;
    private final Playlist model;
    private final Optional<JFrame> app;

    public SongsController(SongsPanel view, Playlist model, JFrame app) {
        this.view = view;
        this.model = model;
        this.view.getSubmitButton().addActionListener(e -> addSong());
        this.app = Optional.ofNullable(app);
    }

    public SongsController(SongsPanel view, Playlist model) {
        this(view, model, null);
    }

    private void addSong() {
        String song = view.getInputField().getText();
        view.getInputField().setText("");
        if (song.isBlank()) return;

        model.add(song);
        SongStatusPanel songStatusPanel = new SongStatusPanel(song);
        songStatusPanel.recentlyAddedView();
        songStatusPanel.getEditButton().addActionListener(e -> {
            view.getSongsView().remove(songStatusPanel);
            view.getSongsView().repaint();
        });
        view.addSongView(songStatusPanel);

        this.app.ifPresent(Window::pack);
    }
}
