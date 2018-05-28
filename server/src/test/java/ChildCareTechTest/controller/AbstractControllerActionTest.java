package ChildCareTechTest.controller;

import ChildCareTech.model.iEntity;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Contains simple setup and teardown methods for a controller action to be tested.
 */
public abstract class AbstractControllerActionTest {
    /**
     * The method that tests the action.
     */
    @Test
    public abstract void testAction();

    /**
     * Configure and start the testing database.
     * @throws Exception
     */
    @Before
    public void setUp()   {
        // setup the session factory
        HibernateSessionFactoryUtil.startHibernate();
    }

    /**
     * After every test, the database is dropped.
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = session.beginTransaction();
        session.createSQLQuery("drop database test").executeUpdate();
        tx.commit();
        session.close();
        HibernateSessionFactoryUtil.close();
    }
}
