package ChildCareTech.model;

import ChildCareTech.utils.GenericDao;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;

import static org.junit.Assert.fail;

public class StopTest extends AbstractEntityTest<Stop, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new GenericDao<>(Stop.class);
    }

    @Override
    public void testCRUD() {
        Trip t = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(1));

        session = sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.save(t);
            tx.commit();
        } catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Stop s = new Stop(t, 0);
        Stop su = new Stop(t, 1);

        testCRUDImpl(s, su);
    }
}
