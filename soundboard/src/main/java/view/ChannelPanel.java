package view;

import javax.swing.*;

/**
 * View that displays the Channel the Bot has joined,
 * and provides an interface to change the Bot's channel.
 */
public class ChannelPanel extends JPanel {

    private final JComboBox<String> channelSelector;
    private final JButton leave;

    /**
     * Empty view that can display information. Needs to be initialised by the ChannelController.
     */
    public ChannelPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.channelSelector = new JComboBox<>();
        channelSelector.setToolTipText("Join a Channel.");
        this.leave = new JButton();
        leave.setToolTipText("Leave Channel.");
    }

    public JComboBox<String> getChannelSelector() {
        return channelSelector;
    }

    public JButton getLeave() {
        return leave;
    }
}
