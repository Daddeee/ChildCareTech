package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.model.DAO.DishDAO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.utils.DTO.DTOEntityAssembler;
import ChildCareTech.utils.DTO.DTOFactory;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DishController {
    public DishController() {}

    public void doDeleteDish(DishDTO dishDTO) {
        DishDAO dishDAO = new DishDAO();
        Dish dish = DTOEntityAssembler.getEntity(dishDTO);

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        dishDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            dishDAO.delete(dish);

            tx.commit();
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void doUpdateDish(DishDTO dishDTO) {
        DishDAO dishDAO = new DishDAO();
        Dish dish = DTOEntityAssembler.getEntity(dishDTO);

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        dishDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            dishDAO.update(dish);

            tx.commit();
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void doCreateDish(DishDTO dishDTO) {
        DishDAO dishDAO = new DishDAO();
        Dish dish = DTOEntityAssembler.getEntity(dishDTO);

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        dishDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            dishDAO.create(dish);

            tx.commit();
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

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
            result.add(DTOFactory.getDTO(d));

        return result;
    }
}
