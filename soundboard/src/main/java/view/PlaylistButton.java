package view;

import model.Playlist;

import javax.swing.*;

public class PlaylistButton extends JButton {
    public PlaylistButton(Playlist playlist) {
        super();
        this.setText(playlist.getTitle());
        this.setToolTipText(playlist.getTitle());
    }
}
