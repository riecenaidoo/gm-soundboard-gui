import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A dummy server that allows a single client connection at a time.
 * Responds to any request with a 200 code.
 */
public class SingleServer implements Runnable {

    static final int PORT = 5000;
    static final String hostname = "localhost";

    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedWriter out;
    private BufferedReader in;

    /**
     * Instantiates the server socket on an available port.
     *
     * @param PORT available port to host the server on.
     * @throws IOException If an I/O error occurs when opening the socket.
     */
    public SingleServer(int PORT) throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.printf("[INFO] Bound server to PORT %d.\n", PORT);
    }

    public static void main(String[] args) {
        try {
            new SingleServer(PORT).run();
        } catch (IOException e) {
            System.out.printf("""
                    [FATAL] Could not establish a SingleServer over Port:%s.
                    \tReason: '%s'.
                    \tQuickFix: Check if the Port currently in use?""", PORT, e.getMessage());
        }
    }

    /**
     * Blocking. Connects a Client and sets up I/O streams.
     * Disconnects existing Client, if any.
     *
     * @throws IOException If an I/O error occurs when waiting for a connection,
     *                     or while setting up I/O streams.
     */
    public void connectClient() throws IOException {
        if (clientSocket != null) disconnectClient();
        clientSocket = serverSocket.accept();
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("[INFO] Client connected.");
    }

    /**
     * Quietly closes the client socket, disconnecting
     * the connected client, if any.
     */
    public void disconnectClient() {
        if (clientSocket == null) return;

        try {
            clientSocket.close();
        } catch (IOException e) {
            System.out.printf("[WARNING] in disconnectClient: %s", e.getMessage());
        }

        clientSocket = null;
        in = null;
        out = null;
    }

    /**
     * Quietly closes the server socket, causing the server
     * to shut down if it is running.
     */
    public void shutdownServer(){
        System.out.println("[INFO] Shutting down server.");
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.printf("[WARNING] in shutdownServer: %s", e.getMessage());
        }
    }

    public void run() {
        System.out.println("[INFO] Server online.");
        while (!serverSocket.isClosed()) {
            try {
                connectClient();
                while (clientSocket.isConnected()) {
                    String clientRequest = getRequest();
                    if (clientRequest == null) break;
                    sendResponse("200");
                }
                System.out.println("[INFO] Client disconnected.");
            } catch (IOException e) {
                System.out.printf("[WARNING] IOException: '%s'.\n", e.getMessage());
            } finally {
                disconnectClient();
            }
        }
    }

    private String getRequest() throws IOException {
        String clientRequest = in.readLine();
        System.out.printf("[<---] '%s'.\n", clientRequest);
        return clientRequest;
    }

    private void sendResponse(String response) throws IOException {
        out.write(response);
        out.newLine();
        out.flush();
        System.out.printf("[--->] '%s'.\n", response);
    }
}