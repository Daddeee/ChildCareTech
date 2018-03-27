package ChildCareTech.model;

import ChildCareTech.utils.GenericDao;

public class UserTest extends AbstractEntityTest<User, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new GenericDao<>(User.class);
    }

    @Override
    public void testCRUD() {
        User u = new User("nome", "pass");
        User uu = new User("nome", "passU");

        testCRUDImpl(u, uu);
    }
}
