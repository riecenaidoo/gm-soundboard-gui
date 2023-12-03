package editor.model;

import org.junit.jupiter.api.Test;
import soundboard.model.catalogue.Playlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Confirm the EditablePlaylist only mutates the wrapped Playlist
 * when requested.
 *
 * @see EditablePlaylist
 * @see Playlist
 */
class EditablePlaylistTest {

    @Test
    void addSongs() {
        Playlist playlist = new Playlist("dummy");
        EditablePlaylist editablePlaylist = new EditablePlaylist(playlist);

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
    void removeSongs() {
        Playlist playlist = new Playlist("dummy");
        playlist.add("foo");
        playlist.add("fib");
        playlist.add("fob");
        EditablePlaylist editablePlaylist = new EditablePlaylist(playlist);

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
        Playlist playlist = new Playlist("dummy");
        EditablePlaylist editablePlaylist = new EditablePlaylist(playlist);

        editablePlaylist.updateTitle("test");
        assertEquals("dummy", playlist.getTitle());

        editablePlaylist.saveChanges();
        assertEquals("test", playlist.getTitle());

        editablePlaylist.updateTitle("foo");
        editablePlaylist.clearChanges();
        assertEquals("test", playlist.getTitle());
    }
}