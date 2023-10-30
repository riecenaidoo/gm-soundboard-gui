package controller;

import model.VoiceChannels;
import view.ChannelsPanel;

public class ChannelController {

    private final VoiceChannels model;
    private final ChannelsPanel view;

    public ChannelController(VoiceChannels model, ChannelsPanel view) {
        this.model = model;
        this.view = view;
    }

    public void loadChannels() {
        model.forEach(channel -> view.getChannelSelector().addItem(channel));
    }
}
