package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Singleton container class for the Channel Selector portion of the interface.
 */
public class ChannelSelectorPanel extends JPanel implements ActionListener {

    private static ChannelSelectorPanel INSTANCE;

    private final JComboBox<String> channelList;

    private ChannelSelectorPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        channelList = new JComboBox<>();
        this.add(channelList);
        JButton submit = new JButton("SUBMIT");
        submit.addActionListener(this);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(submit);
        this.setOpaque(true);
    }

    public static ChannelSelectorPanel getChannelSelector() {
        if (INSTANCE == null) INSTANCE = new ChannelSelectorPanel();
        return INSTANCE;
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
        for (String channel : channelSet) {
            channelList.addItem(channel);
        }
    }

    /**
     * This will eventually trigger a POST Request to the server,
     * to update the channel the Bot joins when music is played.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        API.join_channel(channelList.getSelectedItem());
    }
}
