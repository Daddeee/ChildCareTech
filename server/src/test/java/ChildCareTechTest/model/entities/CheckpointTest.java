package ChildCareTechTest.model.entities;

import ChildCareTech.common.EventStatus;
import ChildCareTech.common.EventType;
import ChildCareTechTest.model.AbstractEntityTest;
import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.model.DAO.CheckpointDAO;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.WorkDay;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.fail;

/**
 * Test basic CRUD operations for Checkpoint entities.
 */
public class CheckpointTest extends AbstractEntityTest<Checkpoint, Integer> {
    @Override
    public void setUp()   {
        super.setUp() ;
        dao = new CheckpointDAO();
    }

    @Override
    public void testCRUD() {
        WorkDay w = new WorkDay(LocalDate.now(), LocalTime.MIN, LocalTime.MAX, false);
        Event event = new Event("nome", w, LocalTime.now(), LocalTime.now().plusMinutes(10), EventType.DAILY, EventStatus.WAIT);
        Person p = new Person("fisccode00000000",
                "nome",
                "cognome",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "addr",
                "phone"
        );


        session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(w);
            session.save(event);
            session.save(p);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Checkpoint e = new Checkpoint(event, p, LocalTime.now());
        Checkpoint eu = new Checkpoint(event, p, LocalTime.now());

        testCRUDImpl(e, eu);
    }
}
