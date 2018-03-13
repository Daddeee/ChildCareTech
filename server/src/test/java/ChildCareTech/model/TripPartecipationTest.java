package ChildCareTech.model;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import java.time.LocalDate;
import static org.junit.Assert.fail;

public class TripPartecipationTest extends AbstractEntityTest<TripPartecipation> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = TripPartecipation.class;
    }

    @Override
    public void testCRUD() {
        Person p = new Person(
                "fisccode",
                "nome",
                "cognome",
                LocalDate.now(),
                Person.Sex.MALE,
                "addr",
                "phone"
        );

        Trip t = new Trip("meta");
        Bus b = new Bus("targa");
        Bus bu = new Bus("targaU");

        session = sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.save(p);
            session.save(t);
            session.save(b);
            session.save(bu);
            tx.commit();
        } catch(HibernateException e){
            if(tx!=null) tx.rollback();
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
