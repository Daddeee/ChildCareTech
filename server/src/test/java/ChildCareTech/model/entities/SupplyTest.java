package ChildCareTech.model.entities;

import ChildCareTech.model.AbstractEntityTest;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Supplier;
import ChildCareTech.model.entities.Supply;
import ChildCareTech.model.DAO.SupplyDAO;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;

import static org.junit.Assert.fail;

/**
 * Test basic CRUD operations for Supply entities.
 */
public class SupplyTest extends AbstractEntityTest<Supply, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new SupplyDAO();
    }

    @Override
    public void testCRUD() {
        Person p = new Person("supplcode0000000",
                "name",
                "lastname",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "addr",
                "phone");
        Supplier suppl = new Supplier(p);
        Food f = new Food("cibo", false);

        session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(suppl);
            session.save(f);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        Supply s = new Supply(suppl, f, 0, LocalDate.now());
        Supply su = new Supply(suppl, f, 1, LocalDate.now());

        testCRUDImpl(s, su);
    }
}
