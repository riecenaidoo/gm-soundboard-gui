package soundboard.view.catalogue;

import soundboard.model.catalogue.Group;

import javax.swing.*;
import java.awt.*;

public class GroupPanel extends JPanel {
    public GroupPanel(Group group) {
        super(new GridLayout(0, 2));
        this.setToolTipText(group.getName());
    }
}
