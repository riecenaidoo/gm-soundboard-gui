import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        Client client = Client.getClient();
        API api = new API(client);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        CatalogueSelectorPanel catalogueSelector = new CatalogueSelectorPanel(api);
        catalogueSelector.loadUI("src/main/resources/sample_catalogue.json");
        panel.add(catalogueSelector);

        JPanel mediaPanel = new JPanel();

        MiniplayerPanel miniPlayer = new MiniplayerPanel(api);
        mediaPanel.add(miniPlayer);

        ChannelSelectorPanel channelSelector = new ChannelSelectorPanel(api);
        channelSelector.populateChannelList(new String[]{"0", "1"});
        mediaPanel.add(channelSelector);

        panel.add(mediaPanel);

        //Create and set up the window.
        JFrame frame = new JFrame("Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
