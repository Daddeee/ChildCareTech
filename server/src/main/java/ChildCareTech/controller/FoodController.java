package ChildCareTech.controller;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.UserSessionFacade;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.model.DAO.FoodDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.network.RemoteEventObservable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Provides implementation for methods in the {@link UserSessionFacade} interface
 * that operate with Food entities.
 */
public class FoodController {
    public FoodController() {}

    /**
     * See {@link UserSessionFacade#getAvailableFoods(PersonDTO)}
     *
     * @param personDTO
     * @return
     */
    public Collection<FoodDTO> doGetAvailableFoods(PersonDTO personDTO) {
        FoodDAO foodDAO = new FoodDAO();
        PersonDAO personDAO = new PersonDAO();
        List<FoodDTO> foodsDTOCollection = new ArrayList<>();
        List<Food> foodsCollection = new ArrayList<>();
        Person person = EntityFactoryFacade.getEntity(personDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        foodDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            foodsCollection = foodDAO.getAvailableFoods(person);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Food f : foodsCollection)
            foodsDTOCollection.add(DTOFactoryFacade.getDTO(f));

        return foodsDTOCollection;
    }

    /**
     * See {@link UserSessionFacade#updateFood(FoodDTO)}
     * @param newFoodDTO
     * @throws UpdateFailedException
     */
    public void doUpdateFood(FoodDTO newFoodDTO) throws UpdateFailedException {
        FoodDAO foodDAO = new FoodDAO();
        Food newFood = EntityFactoryFacade.getEntity(newFoodDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        foodDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            foodDAO.update(newFood);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.FOOD);
        }catch(IndexOutOfBoundsException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new UpdateFailedException("Non è stato trovato alcun alimento da aggiornare");
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new UpdateFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * See {@link UserSessionFacade#removeFood(FoodDTO)}
     *
     * @param foodDTO
     */
    public void doRemoveFood(FoodDTO foodDTO) {
        FoodDAO foodDAO = new FoodDAO();
        Food food = EntityFactoryFacade.getEntity(foodDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        foodDAO.setSession(session);
        try {
            tx = session.beginTransaction();
            foodDAO.delete(food);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.FOOD);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * See {@link UserSessionFacade#saveFood(FoodDTO)}
     *
     * @param foodDTO
     * @throws AddFailedException
     */
    public void doSaveFood(FoodDTO foodDTO) throws AddFailedException {
        Food food = EntityFactoryFacade.getEntity(foodDTO);
        FoodDAO foodDAO = new FoodDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        foodDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            foodDAO.create(food);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.FOOD);
        } catch(Exception ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
            throw new AddFailedException(ex.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * See {@link UserSessionFacade#getAllFoods()}
     *
     * @return
     */
    public List<FoodDTO> doGetAllFoods() {
        FoodDAO foodDAO = new FoodDAO();
        List<Food> foodsCollection = new ArrayList<>();
        List<FoodDTO> foodsDTOCollection = new ArrayList<>();

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        foodDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            foodsCollection = foodDAO.readAll();

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Food f : foodsCollection)
            foodsDTOCollection.add(DTOFactoryFacade.getDTO(f));

        return foodsDTOCollection;
    }
}
