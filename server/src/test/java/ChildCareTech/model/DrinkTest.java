package ChildCareTech.model;

import ChildCareTech.utils.GenericDao;

public class DrinkTest extends AbstractEntityTest<Drink, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new GenericDao<>(Drink.class);
    }

    @Override
    public void testCRUD() {
        Drink o = new Drink("test", null);
        Drink ou = new Drink("testU", null);

        testCRUDImpl(o, ou);
    }
}
