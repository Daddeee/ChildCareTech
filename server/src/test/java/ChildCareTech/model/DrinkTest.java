package ChildCareTech.model;

import ChildCareTech.model.entities.Drink;
import ChildCareTech.model.DAO.DrinkDAO;

public class DrinkTest extends AbstractEntityTest<Drink, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new DrinkDAO();
    }

    @Override
    public void testCRUD() {
        Drink o = new Drink("test", null);
        Drink ou = new Drink("testU", null);

        testCRUDImpl(o, ou);
    }
}
