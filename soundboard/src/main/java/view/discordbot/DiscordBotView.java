package view.discordbot;

import javax.swing.*;

public class DiscordBotView extends JPanel {

    private final VolumeSlider volumeControl;
    private final ChannelsPanel channelsPanel;

    public DiscordBotView() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        volumeControl = new VolumeSlider();
        channelsPanel = new ChannelsPanel();
    }

    public VolumeSlider getVolumeControl() {
        return volumeControl;
    }

    public ChannelsPanel getChannelsPanel() {
        return channelsPanel;
    }
}
