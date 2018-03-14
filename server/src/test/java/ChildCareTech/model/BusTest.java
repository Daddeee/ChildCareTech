package ChildCareTech.model;

import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.fail;

public class BusTest extends AbstractEntityTest<Bus> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Bus.class;
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        Bus b = new Bus("plate");
        Trip t = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(1));
        Person p1 = new Person("fisc1", "fn1", "ln1", LocalDate.now(), Person.Sex.MALE, "", "");
        Person p2 = new Person("fisc2", "fn2", "ln2", LocalDate.now(), Person.Sex.MALE, "", "");
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


        testOneToMany(b, set, Bus::getTripPartecipations);

    }

    @Override
    public void testCRUD() {
        Bus b = new Bus("targa1");
        Bus bu = new Bus("targa1u");

        testCRUDImpl(b, bu);
    }
}
