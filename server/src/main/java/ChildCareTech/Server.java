package ChildCareTech;

import ChildCareTech.common.exceptions.LoginFailedException;
import ChildCareTech.network.RMI.RMIServer;
import ChildCareTech.network.RMI.RMIUserSessionFactory;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.utils.Settings;

import java.rmi.RemoteException;

public class Server {
    private int rmiPort;
    private int socketPort;
    private RMIServer rmiServer;

    public Server() {
        this.rmiPort = Integer.parseInt(Settings.getProperty("port.rmi"));
        this.socketPort = Integer.parseInt(Settings.getProperty("port.socket"));

        this.rmiServer = new RMIServer(rmiPort);
    }


    public static void main(String[] args) throws RemoteException, LoginFailedException {
        Server server = new Server();

        HibernateSessionFactoryUtil.startHibernate();
        server.start();
    }

    public void start() {
        rmiServer.start();
    }

}
