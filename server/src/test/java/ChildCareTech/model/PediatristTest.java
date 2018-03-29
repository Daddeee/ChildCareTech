package ChildCareTech.model;

import ChildCareTech.utils.GenericDao;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.fail;

public class PediatristTest extends AbstractEntityTest<Pediatrist, String> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new GenericDao<>(Pediatrist.class);
    }

    @Override
    public void testRelations() {
        Person o1 = new Person("generic3",
                "generic1",
                "generic1",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "",
                "");

        Pediatrist p = new Pediatrist(o1);

        Person kp1 = new Person("bimbo1", "n", "c", LocalDate.now(), ChildCareTech.common.Sex.MALE, "", "");
        Person kp2 = new Person("bimbo2", "n", "c", LocalDate.now(), ChildCareTech.common.Sex.MALE, "", "");

        Kid k1 = new Kid(kp1, null, null, p);
        Kid k2 = new Kid(kp2, null, null, p);

        HashSet<Kid> set = new HashSet<>();
        set.add(k1);
        set.add(k2);

        testOneToMany(p, set, Pediatrist::getKids);
    }

    @Override
    public void testCRUD() {
        Person o1 = new Person("generic1",
                "generic1",
                "generic1",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "",
                "");

        Person o2 = new Person("generic2",
                "generic2",
                "generic2",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "",
                "");

        Pediatrist p = new Pediatrist(o1);
        Pediatrist pu = new Pediatrist(o2);

        testCRUDImpl(p, pu);
    }
}
