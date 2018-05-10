package ChildCareTech.model;

import ChildCareTech.common.EventStatus;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.DAO.DishDAO;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.entities.Menu;
import ChildCareTech.model.entities.WorkDay;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.fail;

public class DishTest extends AbstractEntityTest<Dish, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new DishDAO();
    }

    @Override
    public void testCRUD() {
        Canteen c = new Canteen("mensa");
        WorkDay w = new WorkDay(LocalDate.now(), LocalTime.MIN, LocalTime.MAX, false);
        Meal ml = new Meal(c, 0, w, null, null, EventStatus.CLOSED);
        Menu m = new Menu(ml, 0, null);

        session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(c);
            session.save(w);
            session.save(ml);
            session.save(m);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Dish o = new Dish("nome");
        Dish ou = new Dish("nomeu");

        testCRUDImpl(o, ou);
    }
}