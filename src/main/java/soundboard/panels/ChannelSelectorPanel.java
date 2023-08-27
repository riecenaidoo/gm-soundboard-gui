package soundboard.panels;

import soundboard.API;

import javax.swing.*;

/**
 * Channel Selector JPanel that enables joining
 * and leaving channel controls.
 */
public class ChannelSelectorPanel extends JPanel {

    private final API api;
    private final JComboBox<String> channelList;
    private int lastSelectedIndex;

    /**
     * Builds a ChannelSelector JPanel that provides a graphical user interface for the soundboard API.
     *
     * @param api the API this ChannelSelector is providing graphical controls for.
     */
    public ChannelSelectorPanel(API api) {
        super();
        this.api = api;
        this.channelList = buildChannelList();
        this.lastSelectedIndex = 0;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.add(channelList);
        this.add(buildLeaveChannelButton());
    }

    private JComboBox<String> buildChannelList() {
        JComboBox<String> channelList = new JComboBox<>();
        channelList.addItemListener(e -> {
            // Only continue if there was a change from the prev value.
            // Necessary to prevent triggering an API call
            // at the start & when channel list is repopulated.
            if (lastSelectedIndex == channelList.getSelectedIndex()) return;

            if (channelList.getSelectedItem() == "") {
                api.leave();
            } else {
                api.join_channel(channelList.getSelectedItem());
            }

            lastSelectedIndex = channelList.getSelectedIndex();
        });
        return channelList;
    }

    private JButton buildLeaveChannelButton() {
        JButton leaveChannelButton = new JButton("LEAVE");
        leaveChannelButton.addActionListener(e -> {
            channelList.setSelectedIndex(0);    // Triggers Channel List's 'leave' call.
        });
        leaveChannelButton.setAlignmentX(CENTER_ALIGNMENT);
        return leaveChannelButton;
    }

    /**
     * Allows the combo box to be populated with its
     * selections after it has been created.
     * <br><br>
     * This is needed because the content for the combo
     * box is fetched from the server via a GET Request.
     * This also allows for updating the contents of the combo
     * box dynamically.
     *
     * @param channelSet names of the join-able voice channels
     *                   for the Bot in Discord.
     */
    public void populateChannelList(String[] channelSet) {
        channelList.removeAllItems();
        channelList.addItem("");
        for (String channel : channelSet) {
            channelList.addItem(channel);
        }

        channelList.setSelectedIndex(0);
    }
}
