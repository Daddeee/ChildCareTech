package ChildCareTech.model;

import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.Assert.fail;

public class PersonTest extends AbstractEntityTest<Person> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Person.class;
    }

    @Override
    public void testRelations() {
        Transaction tx = null;

        WorkDay wd = new WorkDay(LocalDate.now());
        WorkDay wd2 = new WorkDay(LocalDate.now().plusDays(1));
        Person p = new Person("cf", "fn", "ln", LocalDate.now(), Person.Sex.MALE, "", "");
        Event e1 = new Event(wd, p, LocalTime.now(), false);
        Event e2 = new Event(wd2, p, LocalTime.now().plusHours(1), false);

        session = HibernateSessionFactoryUtil.getInstance().openSession();
        try {
            tx = session.beginTransaction();

            session.save(wd);
            session.save(wd2);

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

        HashSet<Event> set = new HashSet<>();
        set.add(e1);
        set.add(e2);

        testOneToMany(p, set, Person::getEvents);
    }


    @Override
    public void testCRUD() {
        Person o = new Person("fisccodetest",
                "name",
                "surname",
                LocalDate.now(),
                Person.Sex.MALE,
                "addr",
                "numb");

        Person ou = new Person("fisccodetest",
                "name_updated",
                "surname",
                LocalDate.now(),
                Person.Sex.MALE,
                "addr",
                "numb");

        testCRUDImpl(o, ou);
    }
}