package ChildCareTech.model.adult;

import ChildCareTech.utils.AbstractGenericDAO;

public class AdultDAO extends AbstractGenericDAO<Adult, Integer> {
    public AdultDAO() {
        super(Adult.class);
    }
}