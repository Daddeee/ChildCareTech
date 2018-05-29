package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.UserSessionFacade;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.model.DAO.DishDAO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.network.RemoteEventObservable;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provides implementation for methods in the {@link UserSessionFacade} interface
 * that operate with Dish entities.
 */
public class DishController {
    public DishController() {}

    /**
     * See {@link UserSessionFacade#deleteDish(DishDTO)}
     *
     * @param dishDTO
     */
    public void doDeleteDish(DishDTO dishDTO) {
        DishDAO dishDAO = new DishDAO();
        Dish dish = EntityFactoryFacade.getEntity(dishDTO);

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        dishDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            dishDAO.delete(dish);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.DISH);
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * See {@link UserSessionFacade#updateDish(DishDTO)}
     *
     * @param dishDTO
     */
    public void doUpdateDish(DishDTO dishDTO) throws UpdateFailedException {
        DishDAO dishDAO = new DishDAO();
        Dish dish = EntityFactoryFacade.getEntity(dishDTO);

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        dishDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            dishDAO.update(dish);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.DISH);
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
            throw new UpdateFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * See {@link UserSessionFacade#createDish(DishDTO)}
     *
     * @param dishDTO
     */
    public void doCreateDish(DishDTO dishDTO) {
        DishDAO dishDAO = new DishDAO();
        Dish dish = EntityFactoryFacade.getEntity(dishDTO);

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        dishDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            dishDAO.create(dish);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.DISH);
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * See {@link UserSessionFacade#getAllDishes()}
     *
     * @return
     */
    public List<DishDTO> doGetAllDishes() {
        List<DishDTO> result = new ArrayList<>();
        List<Dish> queryResult = Collections.emptyList();

        DishDAO dishDAO = new DishDAO();
        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        dishDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            queryResult = dishDAO.readAll();
            for(Dish d : queryResult)
                dishDAO.initializeLazyRelations(d);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }

        for(Dish d : queryResult)
            result.add(DTOFactoryFacade.getDTO(d));

        return result;
    }
}
