package editor.view;

import soundboard.model.catalogue.Catalogue;

import javax.swing.*;

public class CatalogueEditorView extends JPanel {

    private final Catalogue catalogue;
    private final GroupsPanel groupsPanel;
    private final PlaylistsPanel playlistsPanel;
    private final PlaylistEditorPanel playlistEditorPanel;

    public CatalogueEditorView(Catalogue catalogue) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.catalogue = catalogue;
        groupsPanel = new GroupsPanel();
        this.add(groupsPanel);
        playlistsPanel = new PlaylistsPanel();
        this.add(playlistsPanel);
        playlistEditorPanel = new PlaylistEditorPanel();
        this.add(playlistEditorPanel);
    }
}
