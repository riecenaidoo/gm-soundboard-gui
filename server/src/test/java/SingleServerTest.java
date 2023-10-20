import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


/**
 * @see SingleServer
 */
class SingleServerTest {

    static final int PORT = SingleServer.PORT;
    static final String HOST = SingleServer.hostname;

    SingleServer server;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        server = new SingleServer(PORT);
        new Thread(() -> {
            server.run();
        }).start();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        server.shutdown();
    }

    /**
     * @return Socket connected to the setUp Server.
     */
    Socket getSocket() {
        try {
            return new Socket(HOST, PORT);
        } catch (SocketException s) {
            throw new RuntimeException("Could not connect.");
        } catch (UnknownHostException | IllegalArgumentException ex) {
            throw new RuntimeException("Invalid host/port specification.");
        } catch (SecurityException | IOException e) {
            throw new RuntimeException("Check firewall, and local machine resources.");
        }
    }

    /**
     * Can connect to the SingleServer
     * over a local port.
     *
     * Disabled: Will be used for other tests.
     */
    @Test
    @Disabled
    void connect() {
        try {
            Socket socket = new Socket(HOST, PORT);
            socket.close();
        } catch (SocketException s) {
            Assertions.fail("Could not connect.");
        } catch (UnknownHostException | IllegalArgumentException ex) {
            throw new RuntimeException("Invalid host/port specification.");
        } catch (SecurityException | IOException e) {
            throw new RuntimeException("Check firewall, and local machine resources.");
        }
    }

    /**
     * Can send one string to the server
     * and receive a response.
     */
    @Test
    void sendReceiveOne() {
        Socket socket = getSocket();
        try {
            PrintStream out = new PrintStream(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            out.println("Hello World.");
            Assertions.assertFalse(out.checkError(), "There was an error sending a request to the Server.");
            Assertions.assertEquals("200", in.readLine(), "Server did not return success code.");
        } catch (IOException e) {
            Assertions.fail("Could not set up input and output streams.");
        }
    }

    /**
     * Can send/receive many string messages to the server.
     */
    @Test
    void sendReceiveMany() {

    }

    /**
     * Can disconnect from the SingleServer and reconnect.
     */
    @Test
    void disconnectReconnect() {

    }
}