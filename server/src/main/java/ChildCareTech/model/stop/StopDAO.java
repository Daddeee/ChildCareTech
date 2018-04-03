package ChildCareTech.model.stop;

import ChildCareTech.utils.GenericDAO;

public class StopDAO extends GenericDAO<Stop, Integer> {
    public StopDAO() {
        super(Stop.class);
    }
}