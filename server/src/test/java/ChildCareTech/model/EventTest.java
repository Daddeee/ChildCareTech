package ChildCareTech.model;

import ChildCareTech.utils.GenericDao;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.fail;

public class EventTest extends AbstractEntityTest<Event, Integer>{
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new GenericDao<>(Event.class);
    }

    @Override
    public void testCRUD() {
        WorkDay w = new WorkDay(LocalDate.now());
        Person p = new Person("fisccode",
                "nome",
                "cognome",
                LocalDate.now(),
                Person.Sex.MALE,
                "addr",
                "phone"
        );


        session = sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.save(w);
            session.save(p);
            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
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
