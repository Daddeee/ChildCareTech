package ChildCareTech.model.kid;

import ChildCareTech.utils.GenericDAO;

public class KidDAO extends GenericDAO<Kid, Integer> {
    public KidDAO() {
        super(Kid.class);
    }
}