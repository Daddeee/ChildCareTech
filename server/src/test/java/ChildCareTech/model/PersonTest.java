package ChildCareTech.model;

import java.sql.Date;
import java.time.LocalDate;

public class PersonTest extends AbstractEntityTest<Person> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Person.class;
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