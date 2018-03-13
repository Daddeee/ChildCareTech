package ChildCareTech.entities;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import static org.junit.Assert.fail;

public class StopTest extends AbstractEntityTest<Stop> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Stop.class;
    }

    @Override
    public void testCRUD() {
        Trip t = new Trip("meta");

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
