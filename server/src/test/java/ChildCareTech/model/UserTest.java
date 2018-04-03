package ChildCareTech.model;

import ChildCareTech.model.user.User;
import ChildCareTech.utils.GenericDAO;

public class UserTest extends AbstractEntityTest<User, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new GenericDAO<>(User.class);
    }

    @Override
    public void testCRUD() {
        User u = new User("nome", "pass");
        User uu = new User("nome", "passU");

        testCRUDImpl(u, uu);
    }
}
