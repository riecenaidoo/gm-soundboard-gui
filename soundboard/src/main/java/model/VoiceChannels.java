package model;

import java.util.ArrayList;

/**
 * Represents a collection of voice channels in the Discord server
 * that the Bot can join, in the order they appear in the Server.
 */
public class VoiceChannels extends ArrayList<String> {

    private int joinedChannel;

    public VoiceChannels() {
        super();
        joinedChannel = -1;
    }

    public int getJoinedChannel() {
        return joinedChannel;
    }

    public boolean setJoinedChannel(int index) {
        if (index < -1 || index >= this.size()) return false;
        joinedChannel = index;
        return true;
    }
}
