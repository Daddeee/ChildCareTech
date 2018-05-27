package ChildCareTech.network.socket;

import ChildCareTech.common.SocketRequest;
import ChildCareTech.common.SocketResponse;
import ChildCareTech.model.entities.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class is used to encapsulate network logic necessary to handle socket requests from a specific client and send back response messages.
 * The class uses a {@link SocketProtocol} instance to map the requests on the correct controller.
 */
public class SocketUserSessionFacade implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private SocketProtocol socketProtocol;
    private User user;
    private volatile boolean running;

    /**
     * Initialize all stream and protocols necessary to receive requests and send responses.
     *
     * @param socket the socket on which to communicate.
     * @throws IOException
     */
    public SocketUserSessionFacade(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.socketProtocol = new SocketProtocol(this);
        this.user = null;
        this.running = true;
    }

    /**
     * The session continuously waits for socket requests, parse them and handle them using the {@link SocketProtocol protocol},
     * then send back responses.
     */
    public void run() {
        while (running) {
            try {
                SocketRequest request = (SocketRequest) in.readObject();
                SocketResponse response = socketProtocol.handleRequest(request);
                out.writeObject(response);
            } catch (Exception e) {
                break;
            }
        }
    }

    /**
     * Shut down the session.
     */
    public void close() {
        try {
            this.running = false;
            this.user = null;
            this.in.close();
            this.out.close();
            this.socket.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * @return the user associated to the session.
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user that will be associated to the session
     */
    public void setUser(User user) {
        this.user = user;
    }
}