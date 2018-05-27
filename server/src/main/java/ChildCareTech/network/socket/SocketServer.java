package ChildCareTech.network.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Socket Server, encapsulates all logic correlated directly to Socket connections.
 */
public class SocketServer implements Runnable{
    private int socketPort;

    /**
     * @param socketPort the port on which to run the Socket server.
     */
    public SocketServer(int socketPort) {
        this.socketPort = socketPort;
    }

    /**
     * Start this socket server in a separate thread.
     */
    public void start() {
        new Thread(this).start();
    }

    /**
     * The server continuously wait for connection requests from clients and for each of them starts
     * a {@link SocketUserSessionFacade} in a separate thread. If some communication error occurs,
     * the server is shut down.
     */
    @Override
    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(socketPort);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

        System.out.println("[SOCKET] Server running on port " + socketPort);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new SocketUserSessionFacade(socket));
            } catch (IOException e) {
                break;
            }
        }

        executor.shutdown();
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}