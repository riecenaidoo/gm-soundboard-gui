package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ChannelSelectorPanel panel = ChannelSelectorPanel.getChannelSelector();
        panel.populateChannelList(new String[]{"General", "Private", "Quiet"});

        //Create and set up the window.
        JFrame frame = new JFrame("Channel Selection Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
