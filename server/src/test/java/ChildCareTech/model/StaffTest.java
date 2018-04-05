package ChildCareTech.model;

import ChildCareTech.model.person.Person;
import ChildCareTech.model.staff.Staff;
import ChildCareTech.model.staff.StaffDAO;

import java.time.LocalDate;

public class StaffTest extends AbstractEntityTest<Staff, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new StaffDAO();
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

        Staff p = new Staff(o1);
        Staff pu = new Staff(o2);

        testCRUDImpl(p, pu);
    }
}