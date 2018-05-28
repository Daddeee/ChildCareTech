package ChildCareTech.model.entities;

import ChildCareTech.common.Sex;
import ChildCareTech.model.AbstractEntityTest;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.DAO.FoodDAO;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Supplier;
import ChildCareTech.model.entities.Supply;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.fail;

/**
 * Test basic CRUD operations for Food entities.
 */
public class FoodTest extends AbstractEntityTest<Food, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new FoodDAO();
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        Person p = new Person("cf00000000000000", "fn", "ln", LocalDate.now(), ChildCareTech.common.Sex.MALE, "", "11");
        Person p2 = new Person("cf00000000000001", "fn", "ln", LocalDate.now(), ChildCareTech.common.Sex.MALE, "", "11");
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

        Person allergy1 = new Person("all1000000000000", "all", "all", LocalDate.now(), Sex.MALE, "", "1");
        Person allergy2 = new Person("all2000000000000", "all", "all", LocalDate.now(), Sex.MALE, "", "2");

        Food allergic1 = new Food("allergico 1", false);
        Food allergic2 = new Food("allergico 2" ,true);

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();

            session.save(allergy1);
            session.save(allergy2);

            session.save(allergic1);
            session.save(allergic2);

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

        Set<Food> allergics = new HashSet<>();
        allergics.add(allergic1);
        allergics.add(allergic2);

        allergy1.setAllergies(allergics);
        allergy2.setAllergies(allergics);

        Set<Person> allergy = new HashSet<>();
        allergy.add(allergy1);
        allergy.add(allergy2);

        allergic1.setAllergies(allergy);
        allergic2.setAllergies(allergy);

        testManyToMany(allergics, allergy, Food::getAllergies);
    }

    @Override
    public void testCRUD() {
        Food o = new Food("test", false);
        Food ou = new Food("test", true);

        testCRUDImpl(o, ou);
    }
}
