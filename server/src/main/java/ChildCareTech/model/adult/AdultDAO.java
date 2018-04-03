package ChildCareTech.model.adult;

import ChildCareTech.utils.GenericDAO;

public class AdultDAO extends GenericDAO<Adult, Integer> {
    public AdultDAO() {
        super(Adult.class);
    }
}