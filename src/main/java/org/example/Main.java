package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Create and set up the window.
        JFrame frame = new JFrame("Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(doMiniplayer());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    static ChannelSelectorPanel doChannelSelector() {
        ChannelSelectorPanel panel = ChannelSelectorPanel.getChannelSelector();
        panel.populateChannelList(new String[]{"General", "Private", "Quiet"});
        return panel;
    }

    static MiniplayerPanel doMiniplayer() {
        MiniplayerPanel panel = MiniplayerPanel.getMiniplayerPanel();
        panel.setSong("Za Song");
        panel.setPlaylist("Za Playlist");
        return panel;
    }
}
