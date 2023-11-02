package soundboard.view;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {

    private final JLabel status;
    private final JButton connect;

    public HomeView() {
        super();
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(250, 60));

        status = new JLabel();
        status.setPreferredSize(new Dimension(80, 20));
        connect = new JButton("Connect");
        connect.setPreferredSize(new Dimension(50, 30));

        this.add(connect, BorderLayout.CENTER);
        this.add(status, BorderLayout.SOUTH);
    }

    public JButton getConnect() {
        return connect;
    }

    public JLabel getStatus() {
        return status;
    }
}
