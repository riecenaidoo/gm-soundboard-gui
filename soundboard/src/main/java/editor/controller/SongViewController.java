package editor.controller;

import editor.view.SongView;

public class SongViewController {

    private final SongView view;
    private SongStatus status;

    protected SongViewController(SongView view, SongStatus status) {
        this.view = view;
        this.status = status;
        this.view.getEditButton().addActionListener(e -> editSong());
    }

    protected SongViewController(SongView view) {
        this(view, SongStatus.EXISTING);
    }

    private void editSong() {
        switch (status) {
            case EXISTING -> {
                view.markedForRemoval();
                status = SongStatus.MARKED_FOR_REMOVAL;
            }
            case RECENTLY_ADDED -> {
                // TODO Delete this view from the Parent.
            }
            case MARKED_FOR_REMOVAL -> {
                view.existing();
                status = SongStatus.EXISTING;
            }
        }
    }

    /**
     * TODO Consider moving this to a Model Class.
     */
    private enum SongStatus {
        EXISTING, RECENTLY_ADDED, MARKED_FOR_REMOVAL
    }
}
