package ChildCareTechTest.model.entities;


import ChildCareTech.common.EventStatus;
import ChildCareTechTest.model.AbstractEntityTest;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.model.DAO.CanteenDAO;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.Assert.fail;

/**
 * Test basic CRUD operations for Canteen entities.
 */
public class CanteenTest extends AbstractEntityTest<Canteen, Integer> {
    @Override
    public void setUp()   {
        super.setUp() ;
        dao = new CanteenDAO();
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        Canteen c = new Canteen("canteen");
        WorkDay w1 = new WorkDay(LocalDate.now(), LocalTime.MIN, LocalTime.MAX, false);
        WorkDay w2 = new WorkDay(LocalDate.now().plusDays(1), LocalTime.MIN, LocalTime.MAX, false);
        Meal m1 = new Meal(c, 1, w1, null, null, EventStatus.CLOSED, null);
        Meal m2 = new Meal(c, 2, w2, null, null, EventStatus.CLOSED, null);

        session = HibernateSessionFactoryUtil.getInstance().openSession();

        try {
            tx = session.beginTransaction();

            session.save(w1);
            session.save(w2);

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

        HashSet<Meal> set = new HashSet<>();
        set.add(m1);
        set.add(m2);

        testOneToMany(c, set, Canteen::getMeals);
    }

    @Override
    public void testCRUD() {
        Canteen o = new Canteen("test");

        Canteen ou = new Canteen("testU");

        testCRUDImpl(o, ou);
    }
}
