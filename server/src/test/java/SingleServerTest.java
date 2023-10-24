import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Small set of tests to check correctness of SingleServer implementation.
 *
 * @see SingleServer
 */
class SingleServerTest {

    static final int PORT = SingleServer.PORT;
    static final String HOST = SingleServer.hostname;

    SingleServer server;
    Socket socket;
    BufferedWriter out;
    BufferedReader in;

    /**
     * Create an instance of the SingleServer, running it in a thread.
     * Connects a Socket and sets up its input and output streams for testing.
     *
     * @throws IOException IOExceptions occurring during the setup process.
     *                     Usually problems with the HOSTNAME / PORT that need to be resolved manually
     *                     before testing can proceed.
     */
    @BeforeEach
    void setUp() throws IOException {
        server = new SingleServer(PORT);
        new Thread(() -> server.run()).start();

        socket = new Socket(HOST, PORT);
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
    }

    @AfterEach
    void tearDown() throws IOException {
        socket.close();
        server.shutdownServer();
    }

    /**
     * Appends the system's newline to message and flushes the stream.
     * @param msg message without newline and/or carriage return characters to write to the stream.
     * @throws IOException IOException that occurs during the writing to stream process to be handled by test case
     * using this method, usually it should fail the test.
     */
    void sendMessage(String msg) throws IOException {
        out.write(msg);
        out.newLine();
        out.flush();
    }

    /**
     * Can send one string to the server
     * and receive a response.
     */
    @Test
    void sendReceiveOne() {
        try {
            sendMessage("Hello World.");
            assertEquals("200", in.readLine(), "Server did not return success code.");
        } catch (IOException e) {
            fail(String.format("I/O Exception during Test: %s", e.getMessage()));
        }
    }

    /**
     * Can send/receive many string messages to the server.
     */
    @Test
    void sendReceiveMany() {
        try {
            sendMessage("First.");
            assertEquals("200", in.readLine(), "Server did not return success code.");

            sendMessage("Second.");
            assertEquals("200", in.readLine(), "Server did not return success code.");
        } catch (IOException e) {
            fail(String.format("I/O Exception during Test: %s", e.getMessage()));
        }
    }

    /**
     * Can disconnect from the SingleServer and reconnect.
     */
    @Test
    void disconnectReconnect() {
        try {
            sendMessage("Before.");
            assertEquals("200", in.readLine(), "Server did not return success code.");
        } catch (IOException e) {
            fail(String.format("I/O Exception during Test: %s", e.getMessage()));
        }

        try {
            socket.close();
        } catch (IOException e) {
            // IOException here is probably not caused by the Server, so don't fail the test.
            System.out.printf("[WARNING] During Socket Closing: %s\n.", e.getMessage());
        }

        try {
            socket = new Socket(HOST, PORT);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            sendMessage("After.");
            assertEquals("200", in.readLine(), "Server did not return success code.");
        } catch (IOException e) {
            fail(String.format("I/O Exception during Test: %s", e.getMessage()));
        }
    }
}