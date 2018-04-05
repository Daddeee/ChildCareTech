package ChildCareTech.model;

import ChildCareTech.model.adult.Adult;
import ChildCareTech.model.adult.AdultDAO;
import ChildCareTech.model.person.Person;

import java.time.LocalDate;

public class AdultTest extends AbstractEntityTest<Adult, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new AdultDAO();
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

        Adult a = new Adult(o1);
        Adult au = new Adult(o2);

        testCRUDImpl(a, au);
    }
}
