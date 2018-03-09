package ChildCareTech;

import org.hibernate.Transaction;
import org.junit.Test;

import java.sql.Date;

public class KidTest extends AbstractEntityTest<Kid> {
    @Override
    public void testCRUD() {
        Person bp = new Person("test1",
                "bambino",
                "bambino",
                new Date(System.currentTimeMillis()),
                Person.Sex.MALE,
                "addr",
                "333");
        Person tp = new Person("test2",
                "tutore",
                "tutore",
                new Date(System.currentTimeMillis()),
                Person.Sex.MALE,
                "addr",
                "333");

        Adult ta = new Adult(tp);
        Kid k = new Kid(bp, ta, null, null);
        Kid ku = new Kid(bp, null, ta, null);
    }

    @Test(expected = javax.persistence.PersistenceException.class)
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
