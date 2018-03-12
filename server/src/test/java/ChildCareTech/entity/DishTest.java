package ChildCareTech.entity;

import ChildCareTech.Dish;
import ChildCareTech.Menu;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import static org.junit.Assert.fail;

public class DishTest extends AbstractEntityTest<Dish> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Dish.class;
    }

    @Override
    public void testCRUD() {
        Menu o1 = new Menu();
        Menu o2 = new Menu();

        session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            /* creating */
            tx = session.beginTransaction();
            session.save(o1);
            session.save(o2);
            tx.commit();
        } catch(HibernateException e){
            if (tx!=null)
                tx.rollback();
            e.printStackTrace();
            fail("[!] SETUP ERROR: "+ e.getMessage());
        } finally{
            session.close();
        }

        Dish o = new Dish(o1);
        Dish ou = new Dish(o2);

        testCRUDImpl(o, ou);
    }
}