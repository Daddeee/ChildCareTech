package ChildCareTech;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.sql.Date;

public class PersonTest extends AbstractCRUDTest {
    private Person obj;

    @Override
    protected void createObject() {
        session = sessionFactory.openSession();
        Person p = new Person("fisccodetest",
                "name",
                "surname",
                new Date(System.currentTimeMillis()),
                Person.Sex.MALE,
                "addr",
                "numb");

        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(p);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }
        obj = p;
    }

    @Override
    protected void readObject() {
        session = sessionFactory.openSession();
        Transaction tx = null;
        Person p = null;

        try {
            tx = session.beginTransaction();
            p = (Person) session.get(Person.class, obj.getFiscalCode());
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        assertTrue(obj.equals(p));
    }
}