import java.util.List;

public class API {

    private final Client client;

    public API(Client client) {
        this.client = client;
    }

    void play(List<String> songs) {
        StringBuilder stringBuilder = new StringBuilder("play");

        for (String song : songs) {
            stringBuilder.append(" ").append(song);
        }

        String command = stringBuilder.toString();

//        System.out.printf("[INFO][POST Request] Play these songs: '%s' -> '%s' \n", songs, command);
        client.send(command);
    }

    void join_channel(Object selectedItem) {
//        System.out.printf("[INFO][POST Request] Set the Bot's audio channel to <%s>.\n", selectedItem);
        client.send("join " + selectedItem);
    }

    void set_volume(int volume) {
//        System.out.printf("[INFO][POST Request] Set the Bot's audio level '<%d>'.\n", volume);
        client.send("volume " + volume);
    }

    void resume() {
//        System.out.print("[INFO][POST Request] Pause the audio.\n");
        client.send("resume");
    }

    void pause() {
//        System.out.print("[INFO][POST Request] Resume the audio.\n");
        client.send("pause");
    }

    void skip() {
//        System.out.print("[INFO][POST Request] Skip to the next song.\n");
        client.send("skip");
    }

    void prev() {
//        System.out.print("[INFO][POST Request] Skip to the previous song.\n");
        client.send("prev");
    }
}
