package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.User;
import ChildCareTech.model.AbstractGenericDAO;

public class UserDAO extends AbstractGenericDAO<User, Integer> {
    public UserDAO() {
        super(User.class);
    }

    @Override
    public void initializeLazyRelations(User user) {}
}