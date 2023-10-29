package view;

import model.Category;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends JPanel {
    public CategoryPanel(Category category) {
        super(new GridLayout(0, 2));
        this.setToolTipText(category.getTitle());
        category.getPlaylists().forEach(playlist -> this.add(new PlaylistButton(playlist)));
    }
}
