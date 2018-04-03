package ChildCareTech.common;

import ChildCareTech.common.DTO.KidDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserSession extends Remote{
    void logout() throws RemoteException;
    void saveKid(KidDTO kid) throws RemoteException;
}
