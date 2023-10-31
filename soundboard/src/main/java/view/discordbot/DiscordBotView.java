package view.discordbot;

import javax.swing.*;

public class DiscordBotView extends JPanel {

    private final VolumeSlider volumeControl;
    private final ChannelSelectorPanel channelSelectorPanel;
    private final SongControlsPanel songControlsPanel;

    public DiscordBotView() {
        super();
        volumeControl = new VolumeSlider();
        channelSelectorPanel = new ChannelSelectorPanel();
        songControlsPanel = new SongControlsPanel();

        JPanel audioControls = new JPanel();    //Stack volume & song controls together.
        audioControls.setLayout(new BoxLayout(audioControls, BoxLayout.PAGE_AXIS));
        audioControls.add(volumeControl);
        audioControls.add(songControlsPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));   // Place audio & channel controls side by side.
        this.add(audioControls);
        this.add(channelSelectorPanel);
    }

    public VolumeSlider getVolumeControl() {
        return volumeControl;
    }

    public ChannelSelectorPanel getChannelsPanel() {
        return channelSelectorPanel;
    }

    public SongControlsPanel getSongControlsPanel() {
        return songControlsPanel;
    }
}
