package ChildCareTech.model;

public class UserTest extends AbstractEntityTest<User> {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.clazz = User.class;
    }

    @Override
    public void testCRUD() {
        User u = new User("nome", "pass");
        User uu = new User("nome", "passU");

        testCRUDImpl(u, uu);
    }
}
