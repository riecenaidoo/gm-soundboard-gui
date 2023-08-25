package org.example;

import java.util.List;

public class API {

    static void play(List<String> songs) {
        StringBuilder stringBuilder = new StringBuilder("play");

        for (String song : songs) {
            stringBuilder.append(" ").append(song);
        }

        String command = stringBuilder.toString();
        System.out.println(command);

        System.out.printf("[POST Request] Play these songs: '%s' -> '%s' \n", songs, command);
    }

    static void join_channel(Object selectedItem) {
        System.out.printf("[POST Request] Set the Bot's audio channel to <%s>.\n", selectedItem);
    }

    static void set_volume(int volume) {
        System.out.printf("[POST Request] Set the Bot's audio level '<%d>'.\n", volume);
    }

    static void resume() {
        System.out.print("[POST Request] Pause the audio.\n");
    }

    static void pause() {
        System.out.print("[POST Request] Resume the audio.\n");
    }

    static void skip() {
        System.out.print("[POST Request] Skip to the next song.\n");
    }

    static void prev() {
        System.out.print("[POST Request] Skip to the previous song.\n");
    }
}
