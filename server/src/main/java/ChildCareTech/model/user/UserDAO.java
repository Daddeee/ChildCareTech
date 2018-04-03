package ChildCareTech.model.user;

import ChildCareTech.utils.GenericDAO;

public class UserDAO extends GenericDAO<User, Integer> {
    public UserDAO() {
        super(User.class);
    }
}