package soundboard.view.catalogue;

import soundboard.model.Icons;
import soundboard.model.catalogue.Playlist;

import javax.swing.*;
import java.awt.*;

public class PlaylistButton extends JButton {
    public PlaylistButton(Playlist playlist) {
        super();
        this.setText(playlist.getTitle());
        this.setToolTipText(playlist.getTitle());
        this.setPreferredSize(new Dimension(Icons.ICON_SIZE * 4, Icons.ICON_SIZE / 2));
    }
}
