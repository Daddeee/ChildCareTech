package ChildCareTechTest.model.entities;

import ChildCareTech.common.EventStatus;
import ChildCareTechTest.model.AbstractEntityTest;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.fail;

/**
 * Test basic CRUD operations for Person entities.
 */
public class PersonTest extends AbstractEntityTest<Person, Integer> {
    @Override
    public void setUp() {
        super.setUp() ;
        dao = new PersonDAO();
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        Person p = new Person("cf00000000000000", "fn", "ln", LocalDate.now(), ChildCareTech.common.Sex.MALE, "", "11");
        Trip t1 = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(1), EventStatus.OPEN);
        Trip t2 = new Trip("meta1", LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), EventStatus.OPEN);
        Bus b = new Bus("AA111AA", 10);
        TripPartecipation tp1 = new TripPartecipation(p, t1, b);
        TripPartecipation tp2 = new TripPartecipation(p, t2, b);

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();
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
        Person o = new Person("fisccodetest0000",
                "name",
                "surname",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "addr",
                "numb");

        Person ou = new Person("fisccodetest0000",
                "name_updated",
                "surname",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "addr",
                "numb");

        testCRUDImpl(o, ou);
    }
}