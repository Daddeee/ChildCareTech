package ChildCareTech.model;

import ChildCareTech.model.canteen.Canteen;
import ChildCareTech.model.dish.Dish;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.model.menu.Menu;
import ChildCareTech.model.menu.MenuDAO;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.fail;

public class MenuTest extends AbstractEntityTest<Menu, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new MenuDAO();
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        Canteen c = new Canteen("canteen");
        WorkDay wd = new WorkDay(LocalDate.now());
        Meal meal = new Meal(c, 1, wd);
        Menu m = new Menu(meal, 1);
        Dish d1 = new Dish("d1", m);
        Dish d12 = new Dish("d2", m);

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();

            session.save(wd);
            session.save(c);
            session.save(meal);

            session.flush();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        HashSet<Dish> set = new HashSet<>();
        set.add(d1);
        set.add(d1);


        testOneToMany(m, set, Menu::getDishes);

    }


    @Override
    public void testCRUD() {
        Canteen c = new Canteen("mensa");
        WorkDay w = new WorkDay(LocalDate.now());
        Meal ml = new Meal(c, 0, w);

        session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(c);
            session.save(w);
            session.save(ml);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
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
