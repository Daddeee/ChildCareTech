package ChildCareTech.model;

import ChildCareTech.model.canteen.Canteen;
import ChildCareTech.model.event.Event;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.model.person.Person;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.utils.GenericDAO;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.Assert.fail;

public class WorkDayTest extends AbstractEntityTest<WorkDay, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new GenericDAO<>(WorkDay.class);
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        WorkDay wd = new WorkDay(LocalDate.now());
        Canteen c1 = new Canteen("canteen1");
        Canteen c2 = new Canteen("canteen2");
        Meal m1 = new Meal(c1, 1, wd);
        Meal m2 = new Meal(c2, 2, wd);
        Person p1 = new Person("generic1",
                "generic1",
                "generic1",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "",
                "");
        Person p2 = new Person("generic2",
                "generic2",
                "generic2",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "",
                "");
        Event e1 = new Event(wd, p1, LocalTime.now(), false);
        Event e2 = new Event(wd, p2, LocalTime.now().plusHours(1), false);


        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();

            session.save(c1);
            session.save(c2);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        HashSet<Meal> set = new HashSet<>();
        set.add(m1);
        set.add(m2);

        testOneToMany(wd, set, WorkDay::getMeals);

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();
            session.load(wd, wd.getPrimaryKey());
            wd.getMeals().remove(m1);
            wd.getMeals().remove(m2);
            tx.commit();

            tx = session.beginTransaction();
            session.delete(wd);

            session.save(p1);
            session.save(p2);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            session.close();
        }

        HashSet<Event> set1 = new HashSet<>();
        set1.add(e1);
        set1.add(e2);

        testOneToMany(wd, set1, WorkDay::getEvents);
    }

    @Override
    public void testCRUD() {
        WorkDay d = new WorkDay(LocalDate.now());
        WorkDay du = new WorkDay(LocalDate.now().plusDays(1));

        testCRUDImpl(d, du);
    }
}
