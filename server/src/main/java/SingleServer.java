import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A dummy server that allows a single connection.
 * Responds to any request with a 200 code.
 */
public class SingleServer {

    static final int PORT = 6000;
    static final String hostname = "localhost";

    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedWriter out;
    private BufferedReader in;

    public SingleServer(int PORT) {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException(String.format("[FATAL] Could not establish a SingleServer over Port:%s.\n", PORT));
        }
    }

    public static void main(String[] args) {
        new SingleServer(PORT).run();
    }

    public void connect() {
        try {
            clientSocket = serverSocket.accept();

            OutputStream outputStream = clientSocket.getOutputStream();
            out = new BufferedWriter(new OutputStreamWriter(outputStream));
            InputStream inputStream = clientSocket.getInputStream();
            in = new BufferedReader(new InputStreamReader(inputStream));
        } catch (IOException e) {
            throw new RuntimeException(("[WARNING] There was a problem connecting & setting up the socket."));
        }
    }

    public void shutdown() {
        closeQuietly();

        try {
            serverSocket.close();
            if (clientSocket != null) clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        connect();
        try {
            String clientRequest;
            do {
                System.out.println("Awaiting...");
                clientRequest = in.readLine();
                System.out.printf("[<---] '%s'.\n", clientRequest);

                String serverResponse = "200";
                System.out.printf("[--->] '%s'.\n", serverResponse);
                out.write(serverResponse);
                out.newLine();
                out.flush();
            } while (clientRequest != null);
        } catch (IOException ex) {
            System.out.println("[INFO] Disconnected.");
        } finally {
            closeQuietly();
        }
    }

    private void closeQuietly() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
        } catch (IOException e) {
            System.out.println("[WARNING] There was a problem closing ServerSocket I/O streams.");

        }
    }
}