package ChildCareTech.model;

import ChildCareTech.utils.GenericDao;

import java.time.LocalDate;

public class AdultTest extends AbstractEntityTest<Adult, String> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new GenericDao<>(Adult.class);
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
