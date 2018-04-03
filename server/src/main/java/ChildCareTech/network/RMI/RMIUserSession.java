package ChildCareTech.network.RMI;

import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.UserSession;
import ChildCareTech.controller.SessionController;
import ChildCareTech.model.person.Person;
import ChildCareTech.model.person.PersonDTOImpl;
import ChildCareTech.model.user.User;
import ChildCareTech.utils.DTOFactory;
import ChildCareTech.utils.GenericDAO;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIUserSession extends UnicastRemoteObject implements UserSession {
    private User user;

    public RMIUserSession(User user) throws RemoteException {
        this.user = user;
    }

    public List<PersonDTO> getAllPeople() throws RemoteException {
        GenericDAO<Person, String> dao = new GenericDAO<Person, String>(Person.class);
        List<Person> personList;
        List<PersonDTO> dtoList = new ArrayList<>();

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        dao.setSession(session);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            personList = dao.readAll();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            personList = new ArrayList<>();
        }finally{
            session.close();
        }

        for (Person p: personList) {
            dtoList.add(DTOFactory.getDTO(p));
        }

        return dtoList;
    }

    @Override
    public void greetWorld() throws RemoteException{
        System.out.println("Hello, world!");
    }

    @Override
    public void logout() throws RemoteException{
        SessionController.removeSession(user.getUserName());
        UnicastRemoteObject.unexportObject(this, true);
    }

    @Override
    public void savePerson(PersonDTO person) throws RemoteException{

    }
}
