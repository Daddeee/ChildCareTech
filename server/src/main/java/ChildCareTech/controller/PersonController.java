package ChildCareTech.controller;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.model.DAO.FoodDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.utils.RemoteEventObservable;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.NoSuchElementException;

public class PersonController {
    public PersonController() {}

    public void doRemoveAllergy(PersonDTO personDTO, FoodDTO foodDTO) {
        PersonDAO personDAO = new PersonDAO();
        FoodDAO foodDAO = new FoodDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        personDAO.setSession(session);
        foodDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            Person person = personDAO.read(personDTO.getId());
            person.getAllergies().removeIf(food -> food.getId() == foodDTO.getId());
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.ALLERGY);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void doAddAllergy(PersonDTO personDTO, FoodDTO foodDTO) throws AddFailedException {
        PersonDAO personDAO = new PersonDAO();
        FoodDAO foodDAO = new FoodDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        personDAO.setSession(session);
        foodDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            Person person = personDAO.read(personDTO.getId());
            Food food = foodDAO.read(foodDTO.getId());
            person.getAllergies().add(food);
            personDAO.update(person);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.ALLERGY);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new AddFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    public PersonDTO doGetPerson(String fiscalCode) {
        PersonDAO personDAO = new PersonDAO();


        Person result = null;
        PersonDTO resultDTO = null;

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            result = personDAO.read("fiscalCode", fiscalCode).get(0);
            personDAO.initializeLazyRelations(result);

            tx.commit();
        } catch(Exception e){
            e.printStackTrace();
            throw new NoSuchElementException(e.getMessage());
        } finally {
            session.close();
        }

        resultDTO = DTOFactoryFacade.getDTO(result);

        return resultDTO;
    }
}
