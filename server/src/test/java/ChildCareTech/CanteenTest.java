package ChildCareTech;

import java.sql.Date;

public class CanteenTest extends AbstractEntityTest<Canteen> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Canteen.class;
    }

    @Override
    public void testCRUD() {
        Canteen o = new Canteen("test");

        Canteen ou = new Canteen("testU");

        testCRUDImpl(o, ou);
    }
}
