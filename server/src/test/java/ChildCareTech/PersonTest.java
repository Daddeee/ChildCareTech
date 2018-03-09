package ChildCareTech;

import java.sql.Date;

public class PersonTest extends AbstractEntityTest<Person> {

    /* need this to avoid reflection in GenericDAO class !! */
    /* fa schifo comunque */
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new GenericDAO<>(Person.class);
    }

    @Override
    public void testCRUD() {
        Person o = new Person("fisccodetest",
                "name",
                "surname",
                new Date(System.currentTimeMillis()),
                Person.Sex.MALE,
                "addr",
                "numb");

        Person ou = new Person("fisccodetest",
                "name_updated",
                "surname",
                new Date(System.currentTimeMillis()),
                Person.Sex.MALE,
                "addr",
                "numb");

        testCRUDImpl(o, ou);
    }
}