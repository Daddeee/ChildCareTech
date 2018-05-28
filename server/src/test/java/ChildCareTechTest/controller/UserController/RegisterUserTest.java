package ChildCareTechTest.controller.UserController;

import ChildCareTech.common.exceptions.RegistrationFailedException;
import ChildCareTech.controller.UserController;
import ChildCareTech.model.DAO.UserDAO;
import ChildCareTech.model.entities.User;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTechTest.controller.AbstractControllerActionTest;
import org.hibernate.Session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Black-box test of {@link UserController#registerUser(String, String)}.
 */
public class RegisterUserTest extends AbstractControllerActionTest {
    /**
     * Testing normal behavior and limit cases for this method:
     * <ul>
     *     <li>creating a User with an already taken username.</li>
     *     <li>creating a User with a userName shorter than 3 characters</li>
     * </ul>
     */
    @Override
    public void testAction() {
        UserDAO userDAO = new UserDAO();

        String username = "un nome utente simpatico";
        String password = "una password molto sicura";

        String userNameTooShort = "aa";

        try{
            UserController.registerUser(username, password);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

        try{
            UserController.registerUser(username, password);
            fail("Exception not thrown");
        } catch (RegistrationFailedException e) {}

        try{
            UserController.registerUser(userNameTooShort, password);
            fail("Exception not thrown");
        } catch (RegistrationFailedException e) {}

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        userDAO.setSession(session);
        User user = userDAO.read("userName", username).get(0);
        session.close();

        assertEquals(user.getUserName(), username);
        assertEquals(user.getPassword(), password);
    }
}
