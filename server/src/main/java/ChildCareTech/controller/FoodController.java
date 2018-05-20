package ChildCareTech.controller;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.model.DAO.FoodDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.DTO.DTOEntityAssembler;
import ChildCareTech.utils.DTO.DTOFactory;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.utils.RemoteEventObservable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FoodController {
    public FoodController() {}

    public Collection<FoodDTO> doGetAvailableFoods(PersonDTO personDTO) {
        FoodDAO foodDAO = new FoodDAO();
        PersonDAO personDAO = new PersonDAO();
        List<FoodDTO> foodsDTOCollection = new ArrayList<>();
        List<Food> foodsCollection = new ArrayList<>();
        Person person = DTOEntityAssembler.getEntity(personDTO);

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
            foodsDTOCollection.add(DTOFactory.getDTO(f));

        return foodsDTOCollection;
    }

    public void doUpdateFood(FoodDTO newFoodDTO) throws UpdateFailedException {
        FoodDAO foodDAO = new FoodDAO();
        Food newFood = DTOEntityAssembler.getEntity(newFoodDTO);

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

    public void doRemoveFood(FoodDTO foodDTO) {
        FoodDAO foodDAO = new FoodDAO();
        Food food = DTOEntityAssembler.getEntity(foodDTO);

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

    public void doSaveFood(FoodDTO foodDTO) throws AddFailedException {
        Food food = DTOEntityAssembler.getEntity(foodDTO);
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
            foodsDTOCollection.add(DTOFactory.getDTO(f));

        return foodsDTOCollection;
    }
}
