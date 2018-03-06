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

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.mariadb.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/test?createDatabaseIfNotExist=true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        sessionFactory = configuration.buildSessionFactory();
    }

    @Override
    protected void tearDown() throws Exception {
        sessionFactory.close();
    }
}
