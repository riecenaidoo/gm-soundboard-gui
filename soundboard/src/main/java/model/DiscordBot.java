package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents the status of the Bot service this application
 * is connected to. e.g. Whether Shuffle mode is on,
 * What song is playing, etc. It is updated by MusicPlayerController
 * which responds to actions by the user, and updates from the websocket.
 */
public class DiscordBot {

    /**
     * Represents a collection of voice channels in the Discord server
     * that the Bot can join, in the order they appear in the Server.
     */
    private final Collection<String> voiceChannels;

    public DiscordBot() {
        voiceChannels = new ArrayList<>();
    }

    public Collection<String> getVoiceChannels() {
        return voiceChannels;
    }
}