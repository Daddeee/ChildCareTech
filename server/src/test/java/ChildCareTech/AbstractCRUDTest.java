package ChildCareTech;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class AbstractCRUDTest<T> extends TestCase {
    protected SessionFactory sessionFactory;
    protected Session session = null;
    protected T obj;

    protected abstract void createObject();
    protected abstract void readObject();
    protected abstract void updateObject();
    protected abstract void destroyObject();

    public void testCRUD(){
        createObject();
        readObject();
        updateObject();
        destroyObject();
    }

    @Override
    protected void setUp() throws Exception {
        // setup the session factory
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    protected void tearDown() throws Exception {
        sessionFactory.close();
    }
}
