package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.model.DAO.DishDAO;
import ChildCareTech.model.DAO.MealDAO;
import ChildCareTech.model.DAO.MenuDAO;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.utils.DTO.DTOEntityAssembler;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.utils.RemoteEventObservable;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MenuController {
    public MenuController() {}

    public void doCreateMenu(MealDTO mealDTO) {
        MealDAO mealDAO = new MealDAO();
        MenuDAO menuDAO = new MenuDAO();

        Meal meal = DTOEntityAssembler.getEntity(mealDTO);

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        mealDAO.setSession(session);
        menuDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            menuDAO.create(meal.getMenu());
            mealDAO.update(meal);

            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.MENU);
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void doUpdateMenu(MealDTO mealDTO) {
        MenuDAO menuDAO = new MenuDAO();
        MealDAO mealDAO = new MealDAO();

        Meal meal = DTOEntityAssembler.getEntity(mealDTO);

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        menuDAO.setSession(session);
        mealDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            menuDAO.update(meal.getMenu());

            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.MENU);
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void doRemoveDishFromMenu(MenuDTO menuDTO, DishDTO dishDTO) {
        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        try{
            tx = session.beginTransaction();

            session.createSQLQuery("delete from Menu_Dish where menus_id = :menuId and dishes_id = :dishId")
                    .setParameter("menuId", menuDTO.getId())
                    .setParameter("dishId", dishDTO.getId())
                    .executeUpdate();

            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.MENU);
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void doAddDishToMenu(MenuDTO menuDTO, DishDTO dishDTO) {
        MenuDAO menuDAO = new MenuDAO();
        DishDAO dishDAO = new DishDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        menuDAO.setSession(session);
        dishDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            Dish dish = dishDAO.read(dishDTO.getId());
            menuDAO.read(menuDTO.getId()).getDishes().add(dish);

            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.MENU);
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
