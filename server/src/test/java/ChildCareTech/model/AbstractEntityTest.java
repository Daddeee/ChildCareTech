package ChildCareTech.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

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
    public void testRelations(){}

    @Test
    public abstract void testCRUD();

    protected <K> void testOneToMany(T ent1, Set<K> ent2, Function<T , Set<K>> getRelationSet) throws IllegalArgumentException{
        session = sessionFactory.openSession();
        Transaction tx = null;
        Iterator<K> ent2Iter = ent2.iterator();
        try {
            tx = session.beginTransaction();

            session.save(ent1);
            while(ent2Iter.hasNext()) {
                session.save(ent2Iter.next());
            }

            session.flush();
            tx.commit();
        } catch(HibernateException e){
            if (tx!=null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally{
            session.close();
        }


        T read = null;
        Set<K> set = new HashSet<>();
        session = sessionFactory.openSession();
        tx = null;
        try{
            tx = session.beginTransaction();
            read = session.get(clazz, ent1.getPrimaryKey());
            getRelationSet.apply(read).size();
            set = getRelationSet.apply(read);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        }finally{
            session.close();
        }

        assertTrue(ent2.equals(set));
    }

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
