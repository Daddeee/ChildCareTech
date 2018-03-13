package ChildCareTech.model;

import java.sql.Date;
import java.time.LocalDate;

public class StaffTest extends AbstractEntityTest<Staff> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Staff.class;
    }

    @Override
    public void testCRUD() {
        Person o1 = new Person("generic1",
                "generic1",
                "generic1",
                LocalDate.now(),
                Person.Sex.MALE,
                "",
                "");

        Person o2 = new Person("generic2",
                "generic2",
                "generic2",
                LocalDate.now(),
                Person.Sex.MALE,
                "",
                "");

        Staff p = new Staff(o1);
        Staff pu = new Staff(o2);

        testCRUDImpl(p, pu);
    }
}