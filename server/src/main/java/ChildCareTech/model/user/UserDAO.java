package ChildCareTech.model.user;

import ChildCareTech.utils.AbstractGenericDAO;

public class UserDAO extends AbstractGenericDAO<User, Integer> {
    public UserDAO() {
        super(User.class);
    }
}