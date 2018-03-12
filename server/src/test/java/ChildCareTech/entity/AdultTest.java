package ChildCareTech.entity;

import ChildCareTech.Adult;
import ChildCareTech.Person;

import java.sql.Date;

public class AdultTest extends AbstractEntityTest<Adult> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Adult.class;
    }

    @Override
    public void testCRUD() {
        Person o1 = new Person("generic1",
                "generic1",
                "generic1",
                new Date(System.currentTimeMillis()),
                Person.Sex.MALE,
                "",
                "");

        Person o2 = new Person("generic2",
                "generic2",
                "generic2",
                new Date(System.currentTimeMillis()),
                Person.Sex.MALE,
                "",
                "");

        Adult a = new Adult(o1);
        Adult au = new Adult(o2);

        testCRUDImpl(a, au);
    }
}
