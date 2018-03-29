package ChildCareTech.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserSession extends Remote{
    List<PersonDTO> getAllPeople() throws RemoteException;
    void greetWorld() throws RemoteException;
    void logout() throws RemoteException;
}
