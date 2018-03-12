package ChildCareTech;

import java.sql.Date;

public class PediatristTest extends AbstractEntityTest<Pediatrist> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Pediatrist.class;
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

        Pediatrist p = new Pediatrist(o1);
        Pediatrist pu = new Pediatrist(o2);

        testCRUDImpl(p, pu);
    }
}
