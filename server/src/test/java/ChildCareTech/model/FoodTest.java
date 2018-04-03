package ChildCareTech.model;

import ChildCareTech.model.food.Food;
import ChildCareTech.model.person.Person;
import ChildCareTech.model.supplier.Supplier;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.utils.GenericDAO;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.fail;

public class FoodTest extends AbstractEntityTest<Food, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new GenericDAO<>(Food.class);
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        Person p = new Person("cf", "fn", "ln", LocalDate.now(), ChildCareTech.common.Sex.MALE, "", "");
        Person p2 = new Person("cf2", "fn", "ln", LocalDate.now(), ChildCareTech.common.Sex.MALE, "", "");
        Food f = new Food("food", false);
        Supplier s = new Supplier(p);
        Supplier ss = new Supplier(p2);
        Supply s1 = new Supply(s, f, 1, LocalDate.now());
        Supply s2 = new Supply(ss, f, 2, LocalDate.now().plusDays(1));

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();

            session.save(s);
            session.save(ss);

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

        HashSet<Supply> set = new HashSet<>();
        set.add(s1);
        set.add(s2);

        testOneToMany(f, set, Food::getSupplies);
    }

    @Override
    public void testCRUD() {
        Food o = new Food("test", false);
        Food ou = new Food("test", false, 1);

        testCRUDImpl(o, ou);
    }
}
