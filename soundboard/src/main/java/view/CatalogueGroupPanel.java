package view;

import model.CatalogueGroup;

import javax.swing.*;
import java.awt.*;

public class CatalogueGroupPanel extends JPanel {
    public CatalogueGroupPanel(CatalogueGroup catalogueGroup) {
        super(new GridLayout(0, 2));
        this.setToolTipText(catalogueGroup.getName());
    }
}
