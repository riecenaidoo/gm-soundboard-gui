package controller;

import model.Channel;
import view.ChannelPanel;

public class ChannelController {

    private final Channel model;
    private final ChannelPanel view;

    public ChannelController(Channel model, ChannelPanel view) {
        this.model = model;
        this.view = view;
    }

    public void load() {
//        view.getChannelSelector();
    }
}
