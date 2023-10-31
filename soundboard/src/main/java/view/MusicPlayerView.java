package view;

import javax.swing.*;

public class MusicPlayerView extends JPanel {

    private final JSlider volumeControl;

    public MusicPlayerView() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        volumeControl = new JSlider(0, 100);
    }

    public JSlider getVolumeControl() {
        return volumeControl;
    }
}
