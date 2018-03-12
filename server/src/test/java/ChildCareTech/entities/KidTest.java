package ChildCareTech.entities;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.fail;

public class KidTest extends AbstractEntityTest<Kid>{
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Kid.class;
    }

    @Override
    public void testCRUD() {
        Person o = new Person("kidtest",
                "kid",
                "kid",
                LocalDate.now(),
                Person.Sex.MALE,
                "addr",
                "numb");
        Person p = new Person("parenttest",
                "parent",
                "parent",
                LocalDate.now(),
                Person.Sex.MALE,
                "addr",
                "numb");

        session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            /* creating */
            tx = session.beginTransaction();
            session.save(o);
            session.save(p);
            tx.commit();
        } catch(HibernateException e){
            if (tx!=null)
                tx.rollback();
            e.printStackTrace();
            fail("[!] SETUP ERROR: "+ e.getMessage());
        } finally{
            session.close();
        }

        Adult a = new Adult(p);

        session = sessionFactory.openSession();
        tx = null;
        try{
            /* creating */
            tx = session.beginTransaction();
            session.save(a);
            tx.commit();
        } catch(HibernateException e){
            if (tx!=null)
                tx.rollback();
            e.printStackTrace();
            fail("[!] SETUP ERROR: "+ e.getMessage());
        } finally{
            session.close();
        }

        Kid k = new Kid(o, a, null, null);
        Kid ku = new Kid(o, null, a, null);

        testCRUDImpl(k, ku);
    }
}
