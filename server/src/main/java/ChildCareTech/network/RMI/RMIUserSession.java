package ChildCareTech.network.RMI;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.UserSession;
import ChildCareTech.controller.SessionController;
import ChildCareTech.model.user.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIUserSession extends UnicastRemoteObject implements UserSession {
    private User user;

    public RMIUserSession(User user) throws RemoteException {
        this.user = user;
    }

    @Override
    public void logout() throws RemoteException {
        SessionController.removeSession(user.getUserName());
        UnicastRemoteObject.unexportObject(this, true);
    }

    @Override
    public void saveKid(KidDTO kidDTO) throws RemoteException {

    }
}
