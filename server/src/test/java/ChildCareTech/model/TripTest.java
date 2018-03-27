package ChildCareTech.model;

import ChildCareTech.utils.GenericDao;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.Assert.fail;

public class TripTest extends AbstractEntityTest<Trip, Integer>{
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new GenericDao<>(Trip.class);
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        Person p1 = new Person("cf", "fn", "ln", LocalDate.now(), Person.Sex.MALE, "", "");
        Person p2 = new Person("cf2", "fn2", "ln2", LocalDate.now().plusDays(1), Person.Sex.MALE, "", "");
        Trip t = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(1));
        Bus b1 = new Bus("plate");
        Bus b2 = new Bus("plate2");
        TripPartecipation tp1 = new TripPartecipation(p1, t, b1);
        TripPartecipation tp2 = new TripPartecipation(p2, t, b2);
        Stop s1 = new Stop(t, 1);
        Stop s2 = new Stop(t, 2);

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();

            session.save(p1);
            session.save(p2);
            session.save(b1);
            session.save(b2);

            tx.commit();
        } catch(HibernateException e) {
            if(tx != null)
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
        } catch(HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        HashSet<Stop> set1 = new HashSet<>();
        set1.add(s1);
        set1.add(s2);

        testOneToMany(t, set1, Trip::getStops);
    }


    @Override
    public void testCRUD() {
        Trip t = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(2));
        Trip tu = new Trip("meta", LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), "note");

        testCRUDImpl(t, tu);
    }
}