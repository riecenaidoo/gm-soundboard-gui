package view;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private final JMenuItem disconnect;

    public MenuBar() {
        super();
        JMenu menu = new JMenu("Options");
        disconnect = new JMenuItem("Disconnect");
        menu.add(disconnect);
        this.add(menu);
    }

    public JMenuItem getDisconnect() {
        return disconnect;
    }
}
