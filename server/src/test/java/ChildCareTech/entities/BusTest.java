package ChildCareTech.entities;

public class BusTest extends AbstractEntityTest<Bus> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Bus.class;
    }

    @Override
    public void testCRUD() {
        Bus b = new Bus("targa1");
        Bus bu = new Bus("targa1u");

        testCRUDImpl(b, bu);
    }
}
