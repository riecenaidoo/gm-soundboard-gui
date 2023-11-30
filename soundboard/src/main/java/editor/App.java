package editor;

import com.formdev.flatlaf.FlatDarkLaf;
import editor.view.CatalogueEditorView;
import soundboard.model.catalogue.Catalogue;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        JFrame app = new JFrame("Catalogue Editor");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setContentPane(new CatalogueEditorView(new Catalogue()));
        app.pack();
        app.setVisible(true);
    }
}
