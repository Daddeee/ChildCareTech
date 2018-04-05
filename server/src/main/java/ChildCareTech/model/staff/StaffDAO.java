package ChildCareTech.model.staff;

import ChildCareTech.utils.AbstractGenericDAO;

public class StaffDAO extends AbstractGenericDAO<Staff, Integer> {
    public StaffDAO() {
        super(Staff.class);
    }
}