package ChildCareTech.model;

public class TripTest extends AbstractEntityTest<Trip>{
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Trip.class;
    }

    @Override
    public void testCRUD() {
        Trip t = new Trip("meta");
        Trip tu = new Trip("meta", "note");

        testCRUDImpl(t, tu);
    }
}