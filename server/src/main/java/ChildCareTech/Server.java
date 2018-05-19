package ChildCareTech;

import ChildCareTech.network.RMI.RMIServer;
import ChildCareTech.network.socket.SocketServer;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.utils.ManualEventScheduler;
import ChildCareTech.utils.Settings;

public class Server {
    private int rmiPort;
    private int socketPort;
    private RMIServer rmiServer;
    private SocketServer socketServer;

    public Server() {
        this.rmiPort = Integer.parseInt(Settings.getProperty("port.rmi"));
        this.socketPort = Integer.parseInt(Settings.getProperty("port.socket"));

        this.rmiServer = new RMIServer(rmiPort);
        this.socketServer = new SocketServer(socketPort);
    }


    public static void main(String[] args) {
        Server server = new Server();

        HibernateSessionFactoryUtil.startHibernate();
        server.start();
        ManualEventScheduler scheduler = new ManualEventScheduler();
        //EventScheduler scheduler = new EventScheduler();
    }

    public void start() {
        rmiServer.start();
        socketServer.start();
    }

}
