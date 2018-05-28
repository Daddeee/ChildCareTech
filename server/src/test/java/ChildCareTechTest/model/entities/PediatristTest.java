package ChildCareTechTest.model.entities;

import ChildCareTechTest.model.AbstractEntityTest;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Pediatrist;
import ChildCareTech.model.DAO.PediatristDAO;
import ChildCareTech.model.entities.Person;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * Test basic CRUD operations for Pediatrist entities.
 */
public class PediatristTest extends AbstractEntityTest<Pediatrist, Integer> {
    @Override
    public void setUp()   {
        super.setUp() ;
        dao = new PediatristDAO();
    }

    @Override
    public void testRelations() {
        Person o1 = new Person("generic300000000",
                "generic1",
                "generic1",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "",
                "11");

        Pediatrist p = new Pediatrist(o1);

        Person kp1 = new Person("bimbo10000000000", "n", "c", LocalDate.now(), ChildCareTech.common.Sex.MALE, "", "01");
        Person kp2 = new Person("bimbo20000000000", "n", "c", LocalDate.now(), ChildCareTech.common.Sex.MALE, "", "02");

        Kid k1 = new Kid(kp1, p, null, p);
        Kid k2 = new Kid(kp2, p, null, p);

        HashSet<Kid> set = new HashSet<>();
        set.add(k1);
        set.add(k2);

        testOneToMany(p, set, Pediatrist::getKids);
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

        Pediatrist p = new Pediatrist(o1);
        Pediatrist pu = new Pediatrist(o2);

        testCRUDImpl(p, pu);
    }
}
