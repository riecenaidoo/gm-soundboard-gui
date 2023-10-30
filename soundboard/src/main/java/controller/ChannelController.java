package controller;

import view.ChannelsPanel;

import javax.swing.*;
import java.util.Collection;

public class ChannelController {

    private final Collection<String> channels;
    private final JComboBox<String> channelSelector;
    private final JButton leaveButton;
    private int currentChannelIndex;

    public ChannelController(Collection<String> channels, ChannelsPanel view) {
        this.channels = channels;
        this.channelSelector = view.getChannelSelector();
        this.leaveButton = view.getLeave();
        currentChannelIndex = 0;
    }

    /**
     * Loads channels in the ChannelsPanel view. Adds an empty item to denote
     * no channel.
     */
    public void loadChannels() {
        if (channelSelector.getItemCount() > 0) channelSelector.removeAll();
        channelSelector.addItem("");
        channelSelector.setSelectedIndex(currentChannelIndex);
        channels.forEach(channelSelector::addItem);
    }

    public void connect(API api) {
        leaveButton.addItemListener(l -> channelSelector.setSelectedIndex(0));

        channelSelector.addItemListener(e -> {
            int selectedIndex = channelSelector.getSelectedIndex();
            if (selectedIndex == currentChannelIndex) return;
            if (channelSelector.getSelectedItem() == "")
                api.leave();   // "" item is the 0 element, and denotes no channel.
            else api.join_channel(selectedIndex);

            currentChannelIndex = selectedIndex;
        });
    }
}
