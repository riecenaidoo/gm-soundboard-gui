import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleServer {

    private final ServerSocket serverSocket;
    private BufferedWriter out;
    private BufferedReader in;

    public SingleServer(int PORT) throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public static void main(String[] args) {
        int PORT = 5000;
        try {
            new SingleServer(PORT).run();
        } catch (IOException e) {
            System.out.printf("[FATAL] Could not establish a SingleServer over Port:%s.\n", PORT);
        }
    }

    public void connect() throws IOException {
        Socket socket = serverSocket.accept();

        OutputStream outputStream = socket.getOutputStream();
        out = new BufferedWriter(new OutputStreamWriter(outputStream));

        InputStream inputStream = socket.getInputStream();
        in = new BufferedReader(new InputStreamReader(inputStream));
    }

    public void run() throws IOException {


        try {
            String clientRequest = in.readLine();
            System.out.printf("[<---] '%s'.\n", clientRequest);

            String serverResponse = "200";
            System.out.printf("[--->] '%s'.\n", serverResponse);
            out.write(serverResponse);
        } catch (IOException ex) {

        } finally {
            in.close();
            out.close();
        }
    }
}