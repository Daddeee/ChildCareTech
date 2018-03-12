package ChildCareTech.entities;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.sql.Date;

import static org.junit.Assert.fail;

public class MealTest extends AbstractEntityTest<Meal> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Meal.class;
    }

    @Override
    public void testCRUD() {
        Canteen c = new Canteen();

        session = sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.save(c);
            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Meal m = new Meal(c, 0, new Date(System.currentTimeMillis()));
        Meal mu = new Meal(c, 1, new Date(System.currentTimeMillis()));

        testCRUDImpl(m, mu);
    }
}
