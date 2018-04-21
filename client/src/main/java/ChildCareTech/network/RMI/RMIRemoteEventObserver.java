package ChildCareTech.network.RMI;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.common.RemoteEventObserver;
import ChildCareTech.services.MainSceneManager;
import javafx.application.Platform;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.List;

public class RMIRemoteEventObserver extends UnicastRemoteObject implements RemoteEventObserver, Serializable {
    private static WorkDayDTO today;

    public RMIRemoteEventObserver() throws RemoteException {}

    @Override
    public void unexport() throws RemoteException {
        UnicastRemoteObject.unexportObject(this, true);
    }

    @Override
    public void update(WorkDayDTO workDayDTO) {
        today = workDayDTO;
        Platform.runLater(() -> MainSceneManager.getHomeController().refresh(today));
    }
}
