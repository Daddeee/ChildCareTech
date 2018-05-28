package ChildCareTechTest.model.entities;

import ChildCareTech.common.EventStatus;
import ChildCareTechTest.model.AbstractEntityTest;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.DAO.BusDAO;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.fail;

/**
 * Test basic CRUD operations for Bus entities.
 */
public class BusTest extends AbstractEntityTest<Bus, Integer> {
    @Override
    public void setUp()   {
        super.setUp() ;
        dao = new BusDAO();
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        Bus b = new Bus("AA111AA", 10);
        Trip t = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(1), EventStatus.OPEN);
        Person p1 = new Person("fisc100000000000", "fn1", "ln1", LocalDate.now(), ChildCareTech.common.Sex.MALE, "", "111");
        Person p2 = new Person("fisc200000000000", "fn2", "ln2", LocalDate.now(), ChildCareTech.common.Sex.MALE, "", "111");
        TripPartecipation tp1 = new TripPartecipation(p1, t, b);
        TripPartecipation tp2 = new TripPartecipation(p2, t, b);

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();

            session.save(t);
            session.save(p1);
            session.save(p2);

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

        HashSet<TripPartecipation> set = new HashSet<>();
        set.add(tp1);
        set.add(tp2);

        testOneToMany(b, set, Bus::getTripPartecipations);
    }

    @Override
    public void testCRUD() {
        Bus b = new Bus("AA111AA", 10);
        Bus bu = new Bus("AA111BB", 11);

        testCRUDImpl(b, bu);
    }
}
