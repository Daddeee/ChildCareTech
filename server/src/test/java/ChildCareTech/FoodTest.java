package ChildCareTech;

public class FoodTest extends AbstractEntityTest<Food> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Food.class;
    }

    @Override
    public void testCRUD() {
        Food o = new Food("test", false);
        Food ou = new Food("test", false, 1);

        testCRUDImpl(o, ou);
    }
}
