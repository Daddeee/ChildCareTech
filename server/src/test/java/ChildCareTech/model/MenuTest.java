package ChildCareTech.model;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import static org.junit.Assert.fail;

public class MenuTest extends AbstractEntityTest<Menu> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Menu.class;
    }

    @Override
    public void testCRUD() {
        Drink d = new Drink("test1", null);
        Drink d1 = new Drink("test2", null);

        session = sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.save(d);
            session.save(d1);
            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Menu m = new Menu(null, d);
        Menu mu = new Menu(null, d1);

        testCRUDImpl(m, mu);
    }
}
