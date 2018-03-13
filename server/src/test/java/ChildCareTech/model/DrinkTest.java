package ChildCareTech.model;

public class DrinkTest extends AbstractEntityTest<Drink> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        clazz = Drink.class;
    }

    @Override
    public void testCRUD() {
        Drink o = new Drink("test", null);
        Drink ou = new Drink("testU", null);

        testCRUDImpl(o, ou);
    }
}
