package ChildCareTechTest.model.entities;

import ChildCareTech.common.EventStatus;
import ChildCareTechTest.model.AbstractEntityTest;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Route;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.fail;

/**
 * Test basic CRUD operations for Trip entities.
 */
public class TripTest extends AbstractEntityTest<Trip, Integer> {
    @Override
    public void setUp()  {
        super.setUp() ;
        dao = new TripDAO();
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        Person p1 = new Person("cf00000000000000", "fn", "ln", LocalDate.now(), ChildCareTech.common.Sex.MALE, "", "1");
        Person p2 = new Person("cf00000000000001", "fn2", "ln2", LocalDate.now().plusDays(1), ChildCareTech.common.Sex.MALE, "", "2");
        Trip t = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(1), EventStatus.OPEN);
        Bus b1 = new Bus("AA111AA", 10);
        Bus b2 = new Bus("AA111AB", 11);
        TripPartecipation tp1 = new TripPartecipation(p1, t, b1);
        TripPartecipation tp2 = new TripPartecipation(p2, t, b2);
        Route s1 = new Route(t, 0, "a", "b", EventStatus.OPEN,null, null);
        Route s2 = new Route(t, 1, "b", "a", EventStatus.OPEN, null, null);


        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();

            session.save(p1);
            session.save(p2);
            session.save(b1);
            session.save(b2);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        HashSet<TripPartecipation> set = new HashSet<>();
        set.add(tp1);
        set.add(tp2);

        testOneToMany(t, set, Trip::getTripPartecipations);

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();
            session.load(t, t.getPrimaryKey());
            t.getTripPartecipations().remove(tp1);
            t.getTripPartecipations().remove(tp2);
            tx.commit();

            tx = session.beginTransaction();
            session.delete(t);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        HashSet<Route> set1 = new HashSet<>();
        set1.add(s1);
        set1.add(s2);

        //testOneToMany(t, set1, Trip::getRoutes);
    }


    @Override
    public void testCRUD() {
        Trip t = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(2), EventStatus.OPEN);
        Trip tu = new Trip("meta",  "note", LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), EventStatus.OPEN);

        testCRUDImpl(t, tu);
    }
}