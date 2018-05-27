package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.User;
import ChildCareTech.model.AbstractGenericDAO;

/**
 * A Data Access Object that operates with User entities.
 */
public class UserDAO extends AbstractGenericDAO<User, Integer> {
    public UserDAO() {
        super(User.class);
    }

    @Override
    public void initializeLazyRelations(User user) {}
}