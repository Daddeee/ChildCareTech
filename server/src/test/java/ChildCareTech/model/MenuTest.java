package ChildCareTech.model;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;

import static org.junit.Assert.fail;

public class MenuTest extends AbstractEntityTest<Menu> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Menu.class;
    }

    @Override
    public void testCRUD() {
        Canteen c = new Canteen("mensa");
        WorkDay w = new WorkDay(LocalDate.now());
        Meal ml = new Meal(c, 0, w);

        session = sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.save(c);
            session.save(w);
            session.save(ml);
            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Menu m = new Menu(ml, 0, null, null);
        Menu mu = new Menu(ml, 1, null, null);

        testCRUDImpl(m, mu);
    }
}
