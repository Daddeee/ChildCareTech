package ChildCareTech.controller;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.model.DAO.*;
import ChildCareTech.model.entities.*;
import ChildCareTech.utils.DTO.DTOEntityAssembler;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.utils.RemoteEventObservable;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

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

    public void doValidateMenu(MenuDTO menuDTO) throws UpdateFailedException {
        MenuDAO menuDAO = new MenuDAO();
        KidDAO kidDAO = new KidDAO();
        PersonDAO personDAO = new PersonDAO();
        DishDAO dishDAO = new DishDAO();
        Menu menu = null;
        List<Kid> allKids = null;

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        menuDAO.setSession(session);
        kidDAO.setSession(session);
        personDAO.setSession(session);
        dishDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            menu = menuDAO.read(menuDTO.getId());
            for(Dish d : menu.getDishes())
                dishDAO.initializeLazyRelations(d);

            allKids = kidDAO.readAll();
            for(Kid k : allKids)
                personDAO.initializellergiesRelation(k.getPerson());

            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.MENU);
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        if(menu != null && allKids != null)
            searchStarvingKids(menu, allKids);
    }

    private void searchStarvingKids(Menu menu, List<Kid> allKids) throws UpdateFailedException {
        boolean ok = true;
        StringBuilder errorMessage = new StringBuilder("I seguenti bambini non hanno almeno 2 piatti a disposizione:\n");

        final int totalDishesInMenu = menu.getDishes().size();
        for(Kid k : allKids){
            List<String> allergies = new ArrayList<>();

            for(Dish d : menu.getDishes()){
                for(Food f : d.getFoods()){
                    if(k.getPerson().getAllergies().contains(f)){
                        allergies.add(f.getName() + " in " + d.getName());
                    }
                }
            }

            if(totalDishesInMenu - allergies.size() < 2){
                ok = false;
                errorMessage.append(k.getPerson().getFiscalCode())
                        .append(" ")
                        .append(k.getPerson().getLastName())
                        .append(" ")
                        .append(k.getPerson().getFirstName())
                        .append(":\n");

                for(String s : allergies) {
                    errorMessage.append("\t").append("- ").append(s).append("\n");
                }
            }
        }

        if(!ok) throw new UpdateFailedException(errorMessage.toString());
    }
}
