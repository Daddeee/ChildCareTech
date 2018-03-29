package ChildCareTech.network.RMI;

import ChildCareTech.common.PersonDTO;
import ChildCareTech.common.UserSession;
import ChildCareTech.model.Person;
import ChildCareTech.model.User;
import ChildCareTech.utils.GenericDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMISession extends UnicastRemoteObject implements UserSession {
    private User user;

    public RMISession(User user) throws RemoteException {
        this.user = user;
    }

    public List<PersonDTO> getAllPeople() throws RemoteException {
        List<Person> personList = new GenericDao<Person, String>(Person.class).readAll();
        List<PersonDTO> dtoList = new ArrayList<>();

        for (Person p: personList) {
            dtoList.add(p.buildDTO());
        }

        return dtoList;
    }

    @Override
    public void greetWorld() throws RemoteException{
        System.out.println("Hello, world!");
    }

    @Override
    public void logout() throws RemoteException{
        UnicastRemoteObject.unexportObject(this, true);
    }
}
