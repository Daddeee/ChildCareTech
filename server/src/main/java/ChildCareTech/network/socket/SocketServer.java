package ChildCareTech.network.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    private int socketPort;

    public SocketServer(int socketPort) {
        this.socketPort = socketPort;
    }

    public void start() {
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
                executor.submit(new SocketUserSession(socket));
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