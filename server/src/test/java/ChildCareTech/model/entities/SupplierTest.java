package ChildCareTech.model.entities;

import ChildCareTech.model.AbstractEntityTest;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Supplier;
import ChildCareTech.model.DAO.SupplierDAO;
import ChildCareTech.model.entities.Supply;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.fail;

/**
 * Test basic CRUD operations for Supplier entities.
 */
public class SupplierTest extends AbstractEntityTest<Supplier, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new SupplierDAO();
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        Person o1 = new Person("generic300000000",
                "generic1",
                "generic1",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "",
                "00");

        Supplier s = new Supplier(o1);
        Food f1 = new Food("food", false);
        Food f2 = new Food("food2", false);
        Supply sup1 = new Supply(s, f1, 1, LocalDate.now());
        Supply sup2 = new Supply(s, f2, 1, LocalDate.now().plusDays(1));

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();

            session.save(f1);
            session.save(f2);

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
        set.add(sup1);
        set.add(sup2);


        testOneToMany(s, set, Supplier::getSupplies);
    }

    @Override
    public void testCRUD() {
        Person o1 = new Person("generic100000000",
                "generic1",
                "generic1",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "",
                "11");

        Person o2 = new Person("generic200000000",
                "generic2",
                "generic2",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "",
                "11");

        Supplier p = new Supplier(o1);
        Supplier pu = new Supplier(o2);

        testCRUDImpl(p, pu);
    }
}