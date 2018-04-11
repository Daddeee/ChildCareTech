package ChildCareTech.model;

import ChildCareTech.utils.AbstractGenericDAO;
import ChildCareTech.utils.exceptions.ValidationFailedException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public abstract class AbstractEntityTest<T extends iEntity, K extends Serializable> {
    protected SessionFactory sessionFactory;
    protected Session session = null;
    protected AbstractGenericDAO<T, K> dao = null;

    @Test
    public void testRelations() {
    }

    @Test
    public abstract void testCRUD();

    protected <T2> void testOneToMany(T ent1, Set<T2> ent2, Function<T, Set<T2>> getRelationSet) throws IllegalArgumentException {
        session = sessionFactory.openSession();
        dao.setSession(session);
        Transaction tx = null;
        Iterator<T2> ent2Iter = ent2.iterator();
        try {
            tx = session.beginTransaction();

            dao.create(ent1);
            while (ent2Iter.hasNext()) {
                session.save(ent2Iter.next());
            }

            session.flush();
            tx.commit();
        } catch (HibernateException | ValidationFailedException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }


        T read = null;
        Set<T2> set = new HashSet<>();
        session = sessionFactory.openSession();
        dao.setSession(session);
        tx = null;
        try {
            tx = session.beginTransaction();
            read = dao.read(ent1);
            getRelationSet.apply(read).size();
            set = getRelationSet.apply(read);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        assertTrue(ent2.equals(set));
    }

    @SuppressWarnings("unchecked")
    protected void testCRUDImpl(T o, T ou) throws IllegalArgumentException {
        session = sessionFactory.openSession();
        dao.setSession(session);
        Transaction tx = null;
        T t = null;


        try {
            /* creating */
            tx = session.beginTransaction();
            dao.create(o);
            tx.commit();

            /* reading */
            t = dao.read(o);

            /* TEST */
            assertTrue(t.equals(o));

            /* Cannot test update like this after big refactoring */
            /*tx = session.beginTransaction();
            dao.update(ou);
            tx.commit();*/

            /* reading */
            t = dao.read((K) o.getPrimaryKey());

            /* TEST */
            assertTrue(t.equals(o));

            /* deleting */
            tx = session.beginTransaction();
            dao.delete(o);
            tx.commit();

            /* reading */
            t = dao.read((K) o.getPrimaryKey());

            assertTrue(t == null);
        } catch (HibernateException | ValidationFailedException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
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
