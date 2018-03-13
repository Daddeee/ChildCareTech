package ChildCareTech.model;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.fail;

public class SupplyTest extends AbstractEntityTest<Supply> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Supply.class;
    }

    @Override
    public void testCRUD() {
        Person p = new Person("supplcode",
                "name",
                "lastname",
                LocalDate.now(),
                Person.Sex.MALE,
                "addr",
                "phone");
        Supplier suppl = new Supplier(p);
        Food f = new Food("cibo", false);

        session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(suppl);
            session.save(f);
            tx.commit();
        } catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Supply s = new Supply(suppl, f, 0, LocalDate.now());
        Supply su = new Supply(suppl, f, 1, LocalDate.now());

        testCRUDImpl(s, su);
    }
}
