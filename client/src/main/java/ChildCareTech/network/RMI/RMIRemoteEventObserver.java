package ChildCareTech.network.RMI;

import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.RemoteEventObserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIRemoteEventObserver extends UnicastRemoteObject implements RemoteEventObserver, Serializable {
    private static WorkDayDTO today;
    private static List<TripDTO> todayTrips;

    public RMIRemoteEventObserver() throws RemoteException {}

    @Override
    public void unexport() throws RemoteException {
        UnicastRemoteObject.unexportObject(this, true);
    }

    @Override
    public void update(WorkDayDTO workDayDTO, List<TripDTO> tripDTOS) {
        today = workDayDTO;
        todayTrips = tripDTOS;
        //Platform.runLater(() -> MainSceneManager.getHomeController().refresh(today, todayTrips));
    }
}
