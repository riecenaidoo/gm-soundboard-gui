package editor;

import com.formdev.flatlaf.FlatDarkLaf;
import soundboard.model.catalogue.Catalogue;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        JFrame app = new JFrame("Catalogue Editor");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setContentPane(new CatalogueEditor(new Catalogue()));
        app.pack();
        app.setVisible(true);
    }
}
