package editor;

import com.formdev.flatlaf.FlatDarkLaf;
import editor.controller.CatalogueEditorController;
import editor.view.CatalogueEditorView;
import soundboard.model.catalogue.Catalogue;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        JFrame app = new JFrame("Catalogue Editor");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Catalogue catalogue = new Catalogue();
        catalogue.loadTemplate();
        CatalogueEditorView view = new CatalogueEditorView(catalogue);
        new CatalogueEditorController(view, catalogue);

        app.setContentPane(view);
        app.pack();
        app.setVisible(true);
    }
}
