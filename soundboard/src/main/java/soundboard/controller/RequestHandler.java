package soundboard.controller;

import soundboard.model.ClientSocket;

import java.util.Collection;

public class RequestHandler {

    private final ClientSocket clientSocket;

    public RequestHandler(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    // Channel Selector Panel

    public void join_channel(Object selectedItem) {
//        System.out.printf("[INFO][POST Request] Set the Bot's audio channel to <%s>.\n", selectedItem);
        clientSocket.send("join " + selectedItem);
    }

    public void leave() {
        clientSocket.send("leave");
    }

    // Catalogue Selector Panel
    public void play(Collection<String> songs) {
        StringBuilder stringBuilder = new StringBuilder("play");

        for (String song : songs) {
            stringBuilder.append(" ").append(song);
        }

        String command = stringBuilder.toString();

//        System.out.printf("[INFO][POST Request] Play these songs: '%s' -> '%s' \n", songs, command);
        clientSocket.send(command);
    }

    // MusicPlayer Panel

    public void stop() {
        clientSocket.send("stop");
    }

    public void resume() {
//        System.out.print("[INFO][POST Request] Pause the audio.\n");
        clientSocket.send("resume");
    }

    public void pause() {
//        System.out.print("[INFO][POST Request] Resume the audio.\n");
        clientSocket.send("pause");
    }

    public void set_volume(int volume) {
//        System.out.printf("[INFO][POST Request] Set the Bot's audio level '<%d>'.\n", volume);
        clientSocket.send("volume " + volume);
    }

    public void skip() {
//        System.out.print("[INFO][POST Request] Skip to the next song.\n");
        clientSocket.send("skip");
    }

    public void prev() {
//        System.out.print("[INFO][POST Request] Skip to the previous song.\n");
        clientSocket.send("prev");
    }

    public void shuffle() {
        clientSocket.send("shuffle");
    }

    public void loop() {
        clientSocket.send("loop");
    }

    public void repeat() {
        clientSocket.send("repeat");
    }

    public void loop_none() {
        clientSocket.send("normal");
    }
}
