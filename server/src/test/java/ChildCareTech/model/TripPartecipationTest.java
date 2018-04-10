package ChildCareTech.model;

import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.model.DAO.TripPartecipationDAO;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;

import static org.junit.Assert.fail;

public class TripPartecipationTest extends AbstractEntityTest<TripPartecipation, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new TripPartecipationDAO();
    }

    @Override
    public void testCRUD() {
        Person p = new Person(
                "fisccode",
                "nome",
                "cognome",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "addr",
                "phone"
        );

        Trip t = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(1));
        Bus b = new Bus("targa", 10);
        Bus bu = new Bus("targaU", 11);

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
