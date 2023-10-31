package view.discordbot;

import javax.swing.*;

public class DiscordBotView extends JPanel {

    private final VolumeSlider volumeControl;
    private final ChannelSelectorPanel channelSelectorPanel;
    private final SongControlsPanel songControlsPanel;

    public DiscordBotView() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        volumeControl = new VolumeSlider();
        channelSelectorPanel = new ChannelSelectorPanel();
        songControlsPanel = new SongControlsPanel();
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
