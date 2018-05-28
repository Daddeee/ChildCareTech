package ChildCareTechTest.model;

import ChildCareTech.model.AbstractGenericDAO;
import ChildCareTech.model.iEntity;
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

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test basic CRUD operations and relationships for an entity.
 * This tests were used to set correct annotations on every entity and provide a correct mapping.
 *
 * @param <T> the type of entity being tested.
 * @param <K> the type of his primary key.
 */
public abstract class AbstractEntityTest<T extends iEntity, K extends Serializable> {
    protected SessionFactory sessionFactory;
    protected Session session = null;
    protected AbstractGenericDAO<T, K> dao = null;


    /**
     * This method must be implemented to pass entities to methods that test relationships.
     */
    @Test
    public void testRelations() {}

    /**
     * This method must be implemented to pass entities to {@link #testCRUDImpl(iEntity, iEntity)}
     */
    @Test
    public abstract void testCRUD();

    /**
     * Test correct settings for OneToMany relationships.
     * At first every entity of the many side is saved in the database, then they are read back and
     * confronted with the initial Set.
     *
     * @param ent1 the one side of the relationship.
     * @param ent2 a Set containing the many side of the relationship.
     * @param getRelationSet a function called on ent1 to get the relation set from the database
     * @param <T2> the type of the many side.
     * @throws IllegalArgumentException
     */
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

    /**
     * Test correct settings for ManyToMany relationships.
     * At first every entity is saved in the database, then they are read back and
     * confronted with the initial Sets.
     *
     * @param ents1 a Set containing the owning side entities.
     * @param ents2 a Set containing the non-owning side entities.
     * @param getRelationSet a function that, called on an entity of the owning side, returns the associated entities.
     * @param <T2>
     * @throws IllegalArgumentException
     */
    protected <T2> void testManyToMany(Set<T> ents1, Set<T2> ents2, Function<T, Set<T2>> getRelationSet) throws IllegalArgumentException {
        session = sessionFactory.openSession();
        dao.setSession(session);
        Transaction tx = null;
        Iterator<T> ents1Iter = ents1.iterator();
        Iterator<T2> ents2Iter = ents2.iterator();
        try {
            tx = session.beginTransaction();

            while (ents1Iter.hasNext()) {
                session.merge(ents1Iter.next());
            }
            while (ents2Iter.hasNext()) {
                session.merge(ents2Iter.next());
            }

            session.flush();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        ents1Iter = ents1.iterator();
        T read = null;
        Set<T2> set = new HashSet<>();
        session = sessionFactory.openSession();
        dao.setSession(session);
        tx = null;
        try {
            while(ents1Iter.hasNext()){
                tx = session.beginTransaction();
                read = dao.read(ents1Iter.next());
                getRelationSet.apply(read).size();
                set = getRelationSet.apply(read);
                tx.commit();

                assertTrue(ents2.containsAll(set) && set.containsAll(ents2));
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * Test basic CRUD operations for the given entity.
     * <ul>
     *     <li>at first we create the entity</li>
     *     <li>then we read it from the database</li>
     *     <li>then we update it with the new entity and read it back</li>
     *     <li>then we delete it.</li>
     * </ul>
     *
     * @param o the entity to be tested
     * @param ou an updated version of the same entity
     * @throws IllegalArgumentException
     */
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

            assertNull(t);
        } catch (HibernateException | ValidationFailedException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * Configure and start the testing database.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        // setup the session factory
        Configuration config = new Configuration().configure();
        sessionFactory = config.buildSessionFactory();
    }

    /**
     * After every test, the database is dropped.
     * @throws Exception
     */
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
