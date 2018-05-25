package ChildCareTech.controller;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.model.DAO.CanteenDAO;
import ChildCareTech.model.DAO.MealDAO;
import ChildCareTech.model.DAO.MenuDAO;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.DTO.factories.CanteenDTOFactory;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.utils.MealsGenerationUtil;
import ChildCareTech.utils.RemoteEventObservable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class CanteenController {
    public CanteenController() {}

    public CanteenDTO doGetCanteenByName(String name) {
        CanteenDAO canteenDAO = new CanteenDAO();
        MealDAO mealDAO = new MealDAO();
        MenuDAO menuDAO = new MenuDAO();
        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        canteenDAO.setSession(session);
        mealDAO.setSession(session);
        menuDAO.setSession(session);
        List<Canteen> result = Collections.emptyList();
        try{
            tx = session.beginTransaction();

            result = canteenDAO.read("name", name);
            if(result.size() == 0) throw new NoSuchElementException("Nessuna mensa trovata");
            canteenDAO.initializeLazyRelations(result.get(0));
            for(Meal m : result.get(0).getMeals()) {
                mealDAO.initializeLazyRelations(m);
                if(m.getMenu() != null)
                    menuDAO.initializeLazyRelations(m.getMenu());
            }

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return DTOFactoryFacade.getDTO(result.get(0));
    }

    public List<String> doGetAllCanteenNames() {
        CanteenDAO canteenDAO = new CanteenDAO();
        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        canteenDAO.setSession(session);
        List<String> names = Collections.emptyList();
        try{
            tx = session.beginTransaction();

            names = canteenDAO.getAllNames();

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return names;
    }

    public void doSaveCanteen(CanteenDTO canteenDTO, List<LocalTime> entryTimeList, List<LocalTime> exitTimeList) throws AddFailedException {
        CanteenDAO canteenDAO = new CanteenDAO();
        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        canteenDAO.setSession(session);
        Canteen canteen;
        try{
            tx = session.beginTransaction();
            canteen = EntityFactoryFacade.getEntity(canteenDTO);
            canteenDAO.create(canteen);
            tx.commit();

            MealsGenerationUtil.generateMeals(canteen, entryTimeList, exitTimeList);
            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.CANTEEN);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            throw new AddFailedException(e.getMessage());
        } finally {
            session.close();
        }

    }

    public List<CanteenDTO> doGetAllCanteenes() {
        List<Canteen> list = new ArrayList<>();
        List<CanteenDTO> DTOList = new ArrayList<>();
        CanteenDTOFactory canteenDTOFactory = new CanteenDTOFactory();
        CanteenDAO canteenDAO = new CanteenDAO();
        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        canteenDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            list = canteenDAO.readAll();

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        for(Canteen canteen : list)
            DTOList.add(canteenDTOFactory.getDTO(canteen));
        return DTOList;
    }

    public void doRemoveCanteen(CanteenDTO canteenDTO) {
        CanteenDAO canteenDAO = new CanteenDAO();
        Canteen canteen = EntityFactoryFacade.getEntity(canteenDTO);

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        canteenDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            canteenDAO.delete(canteen);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.CANTEEN);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
