package ChildCareTechTest.model.entities;

import ChildCareTechTest.model.AbstractEntityTest;
import ChildCareTech.model.entities.Adult;
import ChildCareTech.model.DAO.AdultDAO;
import ChildCareTech.model.entities.Person;

import java.time.LocalDate;

/**
 * Test basic CRUD operations for Adult entities.
 */
public class AdultTest extends AbstractEntityTest<Adult, Integer> {
    @Override
    public void setUp()   {
        super.setUp() ;
        dao = new AdultDAO();
    }

    @Override
    public void testCRUD() {
        Person o1 = new Person("generic100000000",
                "generic1",
                "generic1",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "",
                "222");

        Person o2 = new Person("generic200000000",
                "generic2",
                "generic2",
                LocalDate.now(),
                ChildCareTech.common.Sex.MALE,
                "",
                "111");

        Adult a = new Adult(o1);
        Adult au = new Adult(o2);

        testCRUDImpl(a, au);
    }
}
