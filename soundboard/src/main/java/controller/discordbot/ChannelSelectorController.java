package controller.discordbot;

import controller.API;
import model.DiscordBot;
import model.Icons;
import view.discordbot.ChannelSelectorPanel;

import javax.swing.*;
import java.util.Collection;

class ChannelSelectorController implements DiscordBotController {

    private final Collection<String> channels;
    private final JComboBox<String> channelSelector;
    private final JButton leaveButton;
    private int currentChannelIndex;

    protected ChannelSelectorController(DiscordBot model, ChannelSelectorPanel view) {
        this.channels = model.getVoiceChannels();
        this.channelSelector = view.getChannelSelector();
        this.leaveButton = view.getLeave();
        currentChannelIndex = 0;
    }

    /**
     * Loads channels in the ChannelsPanel view. Adds an empty item to denote
     * no channel.
     */
    public void sync() {
        if (channelSelector.getItemCount() > 0) channelSelector.removeAll();
        channelSelector.addItem("");
        channelSelector.setSelectedIndex(currentChannelIndex);
        channels.forEach(channelSelector::addItem);
    }

    /**
     * Applies Icons to the UI.
     *
     * @param icons instance containing all loaded Icons available.
     */
    public void loadIcons(Icons icons) {
        leaveButton.setIcon(icons.getLeaveIcon());
        leaveButton.setText("");    // Hide Text
    }

    public void connect(API api) {
        leaveButton.addActionListener(l -> channelSelector.setSelectedIndex(0));

        channelSelector.addItemListener(e -> {
            int selectedIndex = channelSelector.getSelectedIndex();
            if (selectedIndex == currentChannelIndex) return;
            if (channelSelector.getSelectedItem() == "")
                api.leave();   // "" item is the 0 element, and denotes no channel.
            else api.join_channel(selectedIndex - 1); // item 0 denotes no channel ^

            currentChannelIndex = selectedIndex;
        });
    }
}
