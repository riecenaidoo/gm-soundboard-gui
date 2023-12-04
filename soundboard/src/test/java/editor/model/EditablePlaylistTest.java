package editor.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soundboard.model.catalogue.Playlist;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Confirm the EditablePlaylist only mutates the wrapped Playlist when requested.
 * <br><br>
 * Implementation of EditablePlaylist is simple, and these tests are very basic.
 * They are here as a safeguard to make sure future changes to EditablePlaylist
 * do not unexpectedly mutate the wrapped Playlist.
 *
 * @see EditablePlaylist
 * @see Playlist
 */
class EditablePlaylistTest {

    Playlist playlist;
    EditablePlaylist editablePlaylist;

    @BeforeEach
    void setUp() {
        playlist = new Playlist("dummy");
        editablePlaylist = new EditablePlaylist(playlist);
    }

    @Test
    void addSong() {
        editablePlaylist.addSong("foo");
        editablePlaylist.addSong("fib");
        editablePlaylist.addSong("fob");
        assertEquals(0, playlist.size(), "Playlist should be empty before saved changes.");

        editablePlaylist.saveChanges();
        assertEquals(3, playlist.size(), "Playlist should have 3 elements after saved changes.");

        editablePlaylist.saveChanges();
        assertEquals(3, playlist.size(), "Playlist should still have 3 elements.");
    }

    @Test
    void removeSong() {
        playlist.add("foo");
        playlist.add("fib");
        playlist.add("fob");

        editablePlaylist.removeSong("foo");
        assertEquals(3, playlist.size());

        editablePlaylist.saveChanges();
        assertEquals(2, playlist.size());

        editablePlaylist.removeSong("foo");
        editablePlaylist.saveChanges();
        assertEquals(2, playlist.size());

        editablePlaylist.removeSong("fib");
        editablePlaylist.removeSong("fob");
        editablePlaylist.clearChanges();
        assertEquals(2, playlist.size());
    }

    @Test
    void updateTitle() {
        editablePlaylist.updateTitle("test");
        assertEquals("dummy", playlist.getTitle());

        editablePlaylist.saveChanges();
        assertEquals("test", playlist.getTitle());

        editablePlaylist.updateTitle("foo");
        editablePlaylist.clearChanges();
        assertEquals("test", playlist.getTitle());
    }

    @Test
    void isRecentlyAdded() {
        assertFalse(editablePlaylist.isRecentlyAdded("foo"));
        editablePlaylist.addSong("foo");
        assertTrue(editablePlaylist.isRecentlyAdded("foo"));

        editablePlaylist.clearChanges();
        assertFalse(editablePlaylist.isRecentlyAdded("foo"));
    }

    @Test
    void isMarkedForRemoval() {
        assertFalse(editablePlaylist.isMarkedForRemoval("foo"));
        editablePlaylist.removeSong("foo");
        assertTrue(editablePlaylist.isMarkedForRemoval("foo"));

        editablePlaylist.clearChanges();
        assertFalse(editablePlaylist.isMarkedForRemoval("foo"));
    }

    @Test
    void addRemoveSong() {
        editablePlaylist.addSong("foo");
        editablePlaylist.removeSong("foo");
        editablePlaylist.saveChanges();
        assertEquals(0, playlist.size());
    }

    @Test
    void addRemoveSongNameClash() {
        playlist.add("foo");
        editablePlaylist.addSong("foo");
        editablePlaylist.removeSong("foo");
        editablePlaylist.saveChanges();
        assertEquals(1, playlist.size());
    }

    @Test
    void undoRemoveSong() {
        playlist.add("foo");
        editablePlaylist.removeSong("foo");
        editablePlaylist.addSong("foo");
        editablePlaylist.saveChanges();
        assertEquals(1, playlist.size());
    }
}