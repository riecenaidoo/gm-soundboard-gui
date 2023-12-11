package editor.model;

import soundboard.model.catalogue.Playlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Editable Playlist that can track changes (edits), without mutating or cloning
 * the original Playlist directly. Allows changes to be rolled back safely.
 *
 * @see Playlist
 */
public class EditablePlaylist {

    private final Playlist playlist;
    private final List<String> markedForRemoval;
    private final List<String> recentlyAdded;
    private String updatedTitle;

    /**
     * Wraps a Playlist, creating an Editable Playlist that can tracks changes
     * to the original, without mutating it.
     *
     * @param playlist mutable Playlist to track changes for.
     */
    public EditablePlaylist(Playlist playlist) {
        this.playlist = playlist;
        markedForRemoval = new ArrayList<>();
        recentlyAdded = new ArrayList<>();
        updatedTitle = playlist.getTitle();
    }

    public void addSong(String song) {
        recentlyAdded.add(song);
    }

    public void undoAddSong(String song) {
        recentlyAdded.remove(song);
    }

    public void removeSong(String song) {
        markedForRemoval.add(song);
    }

    public void undoRemoveSong(String song) {
        markedForRemoval.remove(song);
    }

    public void updateTitle(String title) {
        updatedTitle = title;
    }

    public boolean isRecentlyAdded(String song) {
        return recentlyAdded.contains(song);
    }

    public boolean isMarkedForRemoval(String song) {
        return markedForRemoval.contains(song);
    }

    /**
     * Commits changes to the Playlist. This action is final, and will mutate the original
     * Playlist.
     */
    public void saveChanges() {
        markedForRemoval.forEach(playlist::remove);
        playlist.addAll(recentlyAdded);
        if (!updatedTitle.isBlank()) playlist.setTitle(updatedTitle);
        clearChanges();
    }

    public void clearChanges() {
        markedForRemoval.clear();
        recentlyAdded.clear();
        updatedTitle = playlist.getTitle();
    }
}
