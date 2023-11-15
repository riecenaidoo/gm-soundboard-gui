package soundboard.controller.catalogue;

import soundboard.controller.RequestHandler;
import soundboard.model.catalogue.Playlist;
import soundboard.view.catalogue.PlaylistButton;

import java.util.Arrays;

class PlaylistButtonController {

    private final Playlist playlist;
    private final PlaylistButton button;

    protected PlaylistButtonController(Playlist playlist, PlaylistButton button) {
        this.playlist = playlist;
        this.button = button;
    }

    public void connect(RequestHandler requestHandler) {
        disconnect();
        button.addActionListener(l -> requestHandler.play(playlist));
    }

    public void disconnect() {
        Arrays.stream(button.getActionListeners()).toList().forEach(button::removeActionListener);
    }
}
