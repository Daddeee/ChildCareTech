package ChildCareTech.model;

import java.time.LocalDate;

public class TripTest extends AbstractEntityTest<Trip>{
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Trip.class;
    }

    @Override
    public void testCRUD() {
        Trip t = new Trip("meta", LocalDate.now(), LocalDate.now().plusDays(2));
        Trip tu = new Trip("meta", LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), "note");

        testCRUDImpl(t, tu);
    }
}