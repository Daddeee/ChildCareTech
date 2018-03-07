package ChildCareTech;

import org.hibernate.JDBCException;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;

import javax.persistence.PersistenceException;
import java.sql.Date;

public class KidTest extends AbstractCRUDTest<Kid> {
    @Override
    protected void createObject() {

    }

    @Override
    protected void readObject() {

    }

    @Override
    protected void updateObject() {

    }

    @Override
    protected void destroyObject() {

    }

    @Test(expected = PersistenceException.class)
    public void testCheckConstraint(){
        session = sessionFactory.openSession();
        Person p = new Person("test",
                "firstname",
                "lastname",
                new Date(System.currentTimeMillis()),
                Person.Sex.MALE,
                "addr",
                "333");

        Kid k = new Kid(p, null, null, null);

        Transaction tx = session.beginTransaction();
        session.save(k);
        tx.commit();
    }
}
