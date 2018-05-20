package ChildCareTech.network.RMI;

import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.services.RemoteUpdateService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

public class RMIRemoteEventObserver extends UnicastRemoteObject implements RemoteEventObserver, Serializable {
    private RemoteUpdateService remoteUpdateService;

    public RMIRemoteEventObserver() throws RemoteException {
        this.remoteUpdateService = new RemoteUpdateService();
    }

    @Override
    public void unexport() throws RemoteException {
        UnicastRemoteObject.unexportObject(this, true);
    }

    @Override
    public void update(RemoteUpdatable updatable) {
        remoteUpdateService.handle(updatable);
    }
}
