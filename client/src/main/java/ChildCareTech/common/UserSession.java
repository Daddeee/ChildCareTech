package ChildCareTech.common;

import ChildCareTech.common.DTO.KidDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserSession extends Remote {
    void saveKid(KidDTO kid) throws RemoteException;

    void logout() throws RemoteException;
}
