package view.discordbot;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class SongControlsPanel extends JPanel {

    private final Map<String, JButton> controls;

    public SongControlsPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        controls = new LinkedHashMap<>();   // Ordering is important
        controls.put("shuffle", new JButton());
        controls.put("prev", new JButton());
        controls.put("playPause", new JButton());
        controls.put("stop", new JButton());
        controls.put("skip", new JButton());
        controls.put("loopMode", new JButton());
        controls.forEach(this::add);
    }

    public JButton getButton(String songControl) {
        return controls.get(songControl);
    }
}
