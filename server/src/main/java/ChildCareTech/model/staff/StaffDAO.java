package ChildCareTech.model.staff;

import ChildCareTech.utils.GenericDAO;

public class StaffDAO extends GenericDAO<Staff, Integer> {
    public StaffDAO() {
        super(Staff.class);
    }
}