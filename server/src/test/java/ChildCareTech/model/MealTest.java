package ChildCareTech.model;

import ChildCareTech.model.canteen.Canteen;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.utils.GenericDAO;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;

import static org.junit.Assert.fail;

public class MealTest extends AbstractEntityTest<Meal, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new GenericDAO<>(Meal.class);
    }

    @Override
    public void testCRUD() {
        Canteen c = new Canteen("mensa");
        WorkDay workDay = new WorkDay(LocalDate.now());
        session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(workDay);
            session.save(c);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Meal m = new Meal(c, 0, workDay);
        Meal mu = new Meal(c, 1, workDay);

        testCRUDImpl(m, mu);
    }
}
