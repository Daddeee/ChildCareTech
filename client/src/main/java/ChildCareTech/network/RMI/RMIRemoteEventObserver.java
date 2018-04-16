package ChildCareTech.network.RMI;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.services.MainSceneManager;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.List;

public class RMIRemoteEventObserver extends UnicastRemoteObject implements RemoteEventObserver, Serializable {
    private static WorkDayDTO today;

    public RMIRemoteEventObserver() throws RemoteException {}

    @Override
    public void update(WorkDayDTO workDayDTO) throws RemoteException{
        today = workDayDTO;
        MainSceneManager.getHomeController().refresh(today);
    }
}
