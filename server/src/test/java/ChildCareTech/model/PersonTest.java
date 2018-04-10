package ChildCareTech.model;

import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.Assert.fail;

public class PersonTest extends AbstractEntityTest<Person, String> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new PersonDAO();
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        WorkDay wd = new WorkDay(LocalDate.now());
        WorkDay wd2 = new WorkDay(LocalDate.now().plusDays(1));
        Person p = new Person("cf", "fn", "ln", LocalDate.now(), ChildCareTech.common.Sex.MALE, "", "");
        Event e1 = new Event(wd, p, LocalTime.now(), false);
        Event e2 = new Event(wd2, p, LocalTime.now().plusHours(1), false);
        Trip t1 = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(1));
        Trip t2 = new Trip("meta1", LocalDate.now().plusDays(2), LocalDate.now().plusDays(3));
        Bus b = new Bus("plate", 10);
        TripPartecipation tp1 = new TripPartecipation(p, t1, b);
        TripPartecipation tp2 = new TripPartecipation(p, t2, b);

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();

            session.save(wd);
            session.save(wd2);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        HashSet<Event> set = new HashSet<>();
        set.add(e1);
        set.add(e2);

        testOneToMany(p, set, Person::getEvents);

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();
            session.load(p, p.getPrimaryKey());
            p.getEvents().remove(e1);
            p.getEvents().remove(e2);
            tx.commit();

            tx = session.beginTransaction();
            session.delete(p);

            session.save(t1);
            session.save(t2);
            session.save(b);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        HashSet<TripPartecipation> set1 = new HashSet<>();
        set1.add(tp1);
        set1.add(tp2);

        testOneToMany(p, set1, Person::getTripPartecipations);
    }

    @Override
    public void testCRUD() {
        Person o = new Person("fisccodetest",
                "name",
                "surname",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "addr",
                "numb");

        Person ou = new Person("fisccodetest",
                "name_updated",
                "surname",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "addr",
                "numb");

        testCRUDImpl(o, ou);
    }
}