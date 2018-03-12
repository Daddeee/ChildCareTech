package ChildCareTech.entities;

import java.sql.Date;
import java.time.LocalDate;

public class SupplierTest extends AbstractEntityTest<Supplier> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Supplier.class;
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

        Supplier p = new Supplier(o1);
        Supplier pu = new Supplier(o2);

        testCRUDImpl(p, pu);
    }
}