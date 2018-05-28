package ChildCareTech.controller;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.UserSessionFacade;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.model.DAO.FoodDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.network.RemoteEventObservable;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.NoSuchElementException;

/**
 * Provides implementation for methods in the {@link UserSessionFacade UserSessionFacade} interface
 * that operate with Person entities.
 */
public class PersonController {
    public PersonController() {}

    /**
     * See {@link UserSessionFacade#removeAllergy(PersonDTO, FoodDTO)}
     *
     * @param personDTO
     * @param foodDTO
     */
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

    /**
     * See {@link UserSessionFacade#addAllergy(PersonDTO, FoodDTO)}
     *
     * @param personDTO
     * @param foodDTO
     * @throws AddFailedException
     */
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

    /**
     * See {@link UserSessionFacade#getPerson(String)}
     *
     * @param fiscalCode
     * @return
     */
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
