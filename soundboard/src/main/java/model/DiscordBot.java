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
    private Collection<String> voiceChannels;

    private boolean playing;
    private boolean shuffle;
    private boolean loop;
    private boolean repeat;

    private int volume;

    public DiscordBot() {
        voiceChannels = new ArrayList<>();
        playing = false;
        shuffle = false;
        loop = false;
        repeat = false;
        volume = 0;
    }

    public Collection<String> getVoiceChannels() {
        return voiceChannels;
    }

    public void setVoiceChannels(Collection<String> voiceChannels) {
        this.voiceChannels = voiceChannels;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        if (loop) repeat = false;
        this.loop = loop;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        if (repeat) loop = false;
        this.repeat = repeat;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        if ((volume >= 0) && (volume <= 100)) this.volume = volume;
    }
}