import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


/**
 * @see SingleServer
 */
class SingleServerTest {

    /**
     * Can connect to the SingleServer
     * over a local port.
     */
    @Test
    void connect() {
        try {
            Socket socket = new Socket("localhost", 5000);
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
//        PrintStream out = new PrintStream(socket.getOutputStream(),true);
//        BufferedReader in = new BufferedReader(new InputStreamReader(
//                socket.getInputStream()))
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