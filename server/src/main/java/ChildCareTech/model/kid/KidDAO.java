package ChildCareTech.model.kid;

import ChildCareTech.utils.AbstractGenericDAO;

public class KidDAO extends AbstractGenericDAO<Kid, Integer> {
    public KidDAO() {
        super(Kid.class);
    }
}