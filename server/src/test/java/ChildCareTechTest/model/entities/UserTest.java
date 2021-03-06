package ChildCareTechTest.model.entities;

import ChildCareTechTest.model.AbstractEntityTest;
import ChildCareTech.model.entities.User;
import ChildCareTech.model.DAO.UserDAO;

/**
 * Test basic CRUD operations for User entities.
 */
public class UserTest extends AbstractEntityTest<User, Integer> {
    @Override
    public void setUp()   {
        super.setUp() ;
        dao = new UserDAO();
    }

    @Override
    public void testCRUD() {
        User u = new User("nome", "pass");
        User uu = new User("nome", "passU");

        testCRUDImpl(u, uu);
    }
}
