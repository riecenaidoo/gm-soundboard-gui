package soundboard.controller.discordbot;

import soundboard.controller.RequestHandler;
import soundboard.model.DiscordBot;
import soundboard.model.Icons;
import soundboard.view.discordbot.ChannelSelectorPanel;

import javax.swing.*;
import java.util.Arrays;
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
        if (channelSelector.getItemCount() > 0) channelSelector.removeAllItems();
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
        Icon leaveIcon = icons.getLeaveIcon();
        if (leaveIcon != null) {
            leaveButton.setText("");
            leaveButton.setIcon(leaveIcon);
        } else {
            leaveButton.setText("LEAVE");
        }
    }

    public void connect(RequestHandler requestHandler) {
        Arrays.stream(leaveButton.getActionListeners()).toList().forEach(leaveButton::removeActionListener);
        leaveButton.addActionListener(l -> channelSelector.setSelectedIndex(0));

        Arrays.stream(channelSelector.getActionListeners()).toList().forEach(channelSelector::removeActionListener);
        channelSelector.addItemListener(e -> {
            int selectedIndex = channelSelector.getSelectedIndex();
            if (selectedIndex == currentChannelIndex) return;
            if (channelSelector.getSelectedItem() == "")
                requestHandler.leave();   // "" item is the 0 element, and denotes no channel.
            else requestHandler.join_channel(selectedIndex - 1); // item 0 denotes no channel ^

            currentChannelIndex = selectedIndex;
        });
    }
}
