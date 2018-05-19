package ChildCareTech.network.socket;

import ChildCareTech.common.SocketRequest;
import ChildCareTech.common.SocketResponse;
import ChildCareTech.model.entities.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class SocketUserSession implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private SocketProtocol socketProtocol;
    private User user;
    private volatile boolean running;

    public SocketUserSession(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.socketProtocol = new SocketProtocol(this);
        this.user = null;
        this.running = true;
    }
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}