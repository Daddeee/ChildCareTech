package ChildCareTech.model;

import ChildCareTech.common.EventStatus;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.entities.Menu;
import ChildCareTech.model.DAO.MenuDAO;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.Assert.fail;

public class MenuTest extends AbstractEntityTest<Menu, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new MenuDAO();
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        Canteen c = new Canteen("canteen");
        WorkDay wd = new WorkDay(LocalDate.now(), LocalTime.MIN, LocalTime.MAX, false);
        Meal meal = new Meal(c, 1, wd, null, null, EventStatus.CLOSED, null);
        Menu m1 = new Menu(meal, 1);
        Menu m2 = new Menu(meal, 2);
        Dish d1 = new Dish("d1");
        Dish d2 = new Dish("d2");

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();

            session.save(wd);
            session.save(c);
            session.save(meal);

            session.save(m1);
            session.save(m2);
            session.save(d1);
            session.save(d2);

            session.flush();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        HashSet<Dish> dishSet = new HashSet<>();
        dishSet.add(d1);
        dishSet.add(d2);

        HashSet<Menu> menuSet = new HashSet<>();
        menuSet.add(m1);
        menuSet.add(m2);

        //testManyToMany(menuSet, dishSet, Menu::getDishes);
    }


    @Override
    public void testCRUD() {
        Canteen c = new Canteen("mensa");
        WorkDay w = new WorkDay(LocalDate.now(), LocalTime.MIN, LocalTime.MAX, false);
        Meal ml = new Meal(c, 0, w, null, null, EventStatus.CLOSED, null);

        session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(c);
            session.save(w);
            session.save(ml);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Menu m = new Menu(ml, 0, null);
        Menu mu = new Menu(ml, 1, null);

        testCRUDImpl(m, mu);
    }
}
