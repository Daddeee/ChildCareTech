package ChildCareTech.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public abstract class AbstractEntityTest<T extends iEntity> {
    protected SessionFactory sessionFactory;
    protected Session session = null;
    protected Class<T> clazz;

    @Test
    public abstract void testCRUD();

    @SuppressWarnings("unchecked")
    protected void testCRUDImpl(T o, T ou) throws IllegalArgumentException{
        session = sessionFactory.openSession();
        Transaction tx = null;
        T t = null;


        try{
            /* creating */
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();

            /* reading */
            t = session.get(clazz, o.getPrimaryKey());

            /* TEST */
            assertTrue(t.equals(o));

            /* updating */
            tx = session.beginTransaction();
            ou.setPrimaryKey(o);
            session.merge(ou);
            tx.commit();

            /* reading */
            t = session.get(clazz, o.getPrimaryKey());

            /* TEST */
            assertTrue(t.equals(o));

            /* deleting */
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();

            /* reading */
            t = session.get(clazz, o.getPrimaryKey());

            assertTrue(t == null);
        } catch(HibernateException e){
            if (tx!=null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally{
            session.close();
        }
    }

    @Before
    public void setUp() throws Exception {
        // setup the session factory
        Configuration config = new Configuration().configure();
        sessionFactory = config.buildSessionFactory();
    }

    @After
    public void tearDown() throws Exception {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.createSQLQuery("drop database test").executeUpdate();
        tx.commit();
        session.close();

        sessionFactory.close();
    }
}
