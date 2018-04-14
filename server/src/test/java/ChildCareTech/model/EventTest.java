package ChildCareTech.model;

import ChildCareTech.model.entities.Event;
import ChildCareTech.model.DAO.EventDAO;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.WorkDay;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.fail;

public class EventTest extends AbstractEntityTest<Event, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new EventDAO();
    }

    @Override
    public void testCRUD() {
        WorkDay w = new WorkDay(LocalDate.now(), LocalTime.MIN, LocalTime.MAX, false);
        Person p = new Person("fisccode",
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
            session.save(p);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Event e = new Event(w, p, LocalTime.now(), true);
        Event eu = new Event(w, p, LocalTime.now(), false);

        testCRUDImpl(e, eu);
    }
}
