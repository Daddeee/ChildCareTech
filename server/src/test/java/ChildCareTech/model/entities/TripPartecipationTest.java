package ChildCareTech.model.entities;

import ChildCareTech.common.EventStatus;
import ChildCareTech.model.AbstractEntityTest;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.model.DAO.TripPartecipationDAO;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;

import static org.junit.Assert.fail;

/**
 * Test basic CRUD operations for TripPartecipation entities.
 */
public class TripPartecipationTest extends AbstractEntityTest<TripPartecipation, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new TripPartecipationDAO();
    }

    @Override
    public void testCRUD() {
        Person p = new Person(
                "fisccode00000000",
                "nome",
                "cognome",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "addr",
                "phone"
        );

        Trip t = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(1), EventStatus.OPEN);
        Bus b = new Bus("AA111AA", 10);
        Bus bu = new Bus("AA111BB", 11);

        session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(p);
            session.save(t);
            session.save(b);
            session.save(bu);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        TripPartecipation tp = new TripPartecipation(p, t, b);
        TripPartecipation tpu = new TripPartecipation(p, t, bu);

        testCRUDImpl(tp, tpu);
    }
}
