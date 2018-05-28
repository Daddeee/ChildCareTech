package ChildCareTechTest.model.entities;

import ChildCareTech.common.EventStatus;
import ChildCareTech.common.EventType;
import ChildCareTechTest.model.AbstractEntityTest;
import ChildCareTech.model.entities.*;
import ChildCareTech.model.DAO.WorkDayDAO;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.Assert.fail;

/**
 * Test basic CRUD operations for WorkDay entities.
 */
public class WorkDayTest extends AbstractEntityTest<WorkDay, Integer> {
    @Override
    public void setUp()   {
        super.setUp() ;
        dao = new WorkDayDAO();
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        WorkDay wd = new WorkDay(LocalDate.now(), LocalTime.MIN, LocalTime.MAX, false);
        Canteen c1 = new Canteen("canteen1");
        Canteen c2 = new Canteen("canteen2");
        Meal m1 = new Meal(c1, 1, wd, null, null, EventStatus.CLOSED, null);
        Meal m2 = new Meal(c2, 2, wd, null, null, EventStatus.CLOSED, null);
        Person p1 = new Person("generic100000000",
                "generic100000000",
                "generic1",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "",
                "1");
        Person p2 = new Person("generic200000000",
                "generic2",
                "generic2",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "",
                "");
        Event e1 = new Event("nome1", wd, LocalTime.now(), LocalTime.now().plusMinutes(10), EventType.DAILY, EventStatus.WAIT);
        Event e2 = new Event("nome2", wd, LocalTime.now(), LocalTime.now().plusMinutes(10), EventType.DAILY, EventStatus.WAIT);


        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();

            session.save(c1);
            session.save(c2);

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

        testOneToMany(wd, set, WorkDay::getMeals);

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();
            session.load(wd, wd.getPrimaryKey());
            wd.getMeals().remove(m1);
            wd.getMeals().remove(m2);
            tx.commit();

            tx = session.beginTransaction();
            session.delete(wd);

            session.save(p1);
            session.save(p2);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        HashSet<Event> set1 = new HashSet<>();
        set1.add(e1);
        set1.add(e2);

        //testOneToMany(wd, set1, WorkDay::getEvents);
    }

    @Override
    public void testCRUD() {
        WorkDay d = new WorkDay(LocalDate.now(), LocalTime.MIN, LocalTime.MAX, false);
        WorkDay du = new WorkDay(LocalDate.now().plusDays(1), LocalTime.MIN, LocalTime.MAX, false);

        testCRUDImpl(d, du);
    }
}
