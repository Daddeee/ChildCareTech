package ChildCareTech.model;


import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.fail;

public class CanteenTest extends AbstractEntityTest<Canteen> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Canteen.class;
    }

    @Override
    public void testRelations(){
        Transaction tx = null;

        Canteen c = new Canteen("canteen");
        WorkDay w1 = new WorkDay(LocalDate.now());
        WorkDay w2 = new WorkDay(LocalDate.now().plusDays(1));
        Meal m1 = new Meal(c, 1, w1);
        Meal m2 = new Meal(c, 2, w2);

        session = HibernateSessionFactoryUtil.getInstance().openSession();

        try {
            tx = session.beginTransaction();

            session.save(w1);
            session.save(w2);

            session.flush();
            tx.commit();
        } catch(HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        HashSet<Meal> set = new HashSet<>();
        set.add(m1);
        set.add(m2);

        testOneToMany(c, set, Canteen::getMeals);
    }

    @Override
    public void testCRUD() {
        Canteen o = new Canteen("test");

        Canteen ou = new Canteen("testU");

        testCRUDImpl(o, ou);
    }
}
