package ChildCareTech.common;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserSession extends Remote{
    List<PersonDTO> getAllPeople() throws RemoteException;
    void greetWorld() throws RemoteException;
    void logout() throws RemoteException;
}
