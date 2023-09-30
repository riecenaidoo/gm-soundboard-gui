package soundboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {

    static int PORT = 5000;
    static String SERVER_IP = "localhost";
    static Client INSTANCE;

    Socket socket;
    PrintStream out;
    BufferedReader in;

    /**
     * @throws IOException if an I/O error occurs when creating the output stream
     *                     or if the socket is not connected.
     */
    private Client(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new PrintStream(socket.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * @throws ClientCreationException if an I/O error occurred while setting up the socket.
     */
    public static Client getClient() {
        if (INSTANCE != null) {
            return INSTANCE;
        }

        try {
            Socket socket = new Socket(SERVER_IP, PORT);
            return new Client(socket);
        } catch (IOException e) {
            throw new ClientCreationException(e);
        }
    }

    public void send(String output) {
        out.println(output);
        out.flush();
        System.out.println(receive());
    }

    public String receive() {
        String messageFromServer = null;
        try {
            messageFromServer = in.readLine();
        } catch (IOException e) {
            System.out.printf(
                    """
                            [ERROR] An error occurred while communicating with the server.
                            \tReason: '%s'.
                            """, e.getMessage());
            throw new WebSocketBrokenException();
        } finally {
            if (socket.isClosed()) closeQuietly();
        }

        return messageFromServer;
    }

    private void closeQuietly() {
        try {
            out.close();
            in.close();
            socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();
        } catch (IOException e) {
            System.out.printf(
                    """
                            [ERROR] An error occurred while closing down the client socket.
                            \tReason: '%s'.
                            """, e.getMessage());
        }
    }

    /**
     * Custom exception to separate IOExceptions that can occur during runtime from
     * those that occur when setting up the socket.
     */
    static class ClientCreationException extends RuntimeException {
        ClientCreationException(IOException e) {
            super(String.format("""
                    [FATAL] An I/O error occurred while setting up the socket.
                    \tReason: '%s'.
                    """, e.getMessage()));
        }
    }

    static class WebSocketBrokenException extends RuntimeException {
    }
}