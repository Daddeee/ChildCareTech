package ChildCareTech.model;

import ChildCareTech.common.EventStatus;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.DAO.MealDAO;
import ChildCareTech.model.entities.WorkDay;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.fail;

public class MealTest extends AbstractEntityTest<Meal, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new MealDAO();
    }

    @Override
    public void testCRUD() {
        Canteen c = new Canteen("mensa");
        WorkDay workDay = new WorkDay(LocalDate.now(), LocalTime.MIN, LocalTime.MAX, false);
        session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(workDay);
            session.save(c);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Meal m = new Meal(c, 0, workDay, null, null, EventStatus.CLOSED);
        Meal mu = new Meal(c, 1, workDay, null, null, EventStatus.CLOSED);

        testCRUDImpl(m, mu);
    }
}
