package ChildCareTech.model.entities;

import ChildCareTech.model.AbstractEntityTest;
import ChildCareTech.model.entities.User;
import ChildCareTech.model.DAO.UserDAO;

/**
 * Test basic CRUD operations for User entities.
 */
public class UserTest extends AbstractEntityTest<User, Integer> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new UserDAO();
    }

    @Override
    public void testCRUD() {
        User u = new User("nome", "pass");
        User uu = new User("nome", "passU");

        testCRUDImpl(u, uu);
    }
}
