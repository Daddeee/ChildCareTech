package ChildCareTech.model.entities;

import ChildCareTech.model.AbstractEntityTest;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Staff;
import ChildCareTech.model.DAO.StaffDAO;

import java.time.LocalDate;

/**
 * Test basic CRUD operations for Staff entities.
 */
public class StaffTest extends AbstractEntityTest<Staff, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new StaffDAO();
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

        Staff p = new Staff(o1);
        Staff pu = new Staff(o2);

        testCRUDImpl(p, pu);
    }
}