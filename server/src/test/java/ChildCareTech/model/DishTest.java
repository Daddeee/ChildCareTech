package ChildCareTech.model;

import ChildCareTech.model.canteen.Canteen;
import ChildCareTech.model.dish.Dish;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.model.menu.Menu;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.utils.GenericDAO;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;

import static org.junit.Assert.fail;

public class DishTest extends AbstractEntityTest<Dish, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new GenericDAO<>(Dish.class);
    }

    @Override
    public void testCRUD() {
        Canteen c = new Canteen("mensa");
        WorkDay w = new WorkDay(LocalDate.now());
        Meal ml = new Meal(c, 0, w);
        Menu m = new Menu(ml, 0, null, null);

        session = sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.save(c);
            session.save(w);
            session.save(ml);
            session.save(m);
            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Dish o = new Dish("nome", m);
        Dish ou = new Dish("nomeu", m);

        testCRUDImpl(o, ou);
    }
}