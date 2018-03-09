package ChildCareTech;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public abstract class AbstractEntityTest<T extends DAOEntity> {
    protected SessionFactory sessionFactory;
    protected Session session = null;
    protected GenericDAO<T> dao = null;

    @Test
    public abstract void testCRUD();

    protected void testCRUDImpl(T o, T ou) throws IllegalArgumentException{
        session = sessionFactory.openSession();
        Transaction tx = null;
        T t = null;

        dao.setSession(session);

        try{
            tx = session.beginTransaction();
            dao.create(o);
            tx.commit();

            t = dao.read(o.getPrimaryKey());
            assertTrue(t.equals(o));

            tx = session.beginTransaction();
            dao.update(ou);
            tx.commit();

            t = dao.read(ou.getPrimaryKey());
            assertTrue(t.equals(ou));

            tx = session.beginTransaction();
            session.delete(t);
            tx.commit();

            t = dao.read(ou.getPrimaryKey());
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
