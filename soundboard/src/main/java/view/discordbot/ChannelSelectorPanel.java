package view.discordbot;

import javax.swing.*;

/**
 * View that displays the Channel the Bot has joined,
 * and provides an interface to change the Bot's channel.
 */
public class ChannelSelectorPanel extends JPanel {

    private final JComboBox<String> channelSelector;
    private final JButton leave;

    /**
     * Empty view that can display information. Needs to be initialised by the ChannelController.
     */
    public ChannelSelectorPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        channelSelector = new JComboBox<>();
        channelSelector.setToolTipText("Join a Channel.");
        this.add(channelSelector);

        leave = new JButton("LEAVE");
        leave.setAlignmentX(CENTER_ALIGNMENT);
        leave.setToolTipText("Leave Channel.");
        this.add(leave);
    }

    public JComboBox<String> getChannelSelector() {
        return channelSelector;
    }

    public JButton getLeave() {
        return leave;
    }
}
