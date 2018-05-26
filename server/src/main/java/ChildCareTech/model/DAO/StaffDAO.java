package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Staff;
import ChildCareTech.model.AbstractGenericDAO;

public class StaffDAO extends AbstractGenericDAO<Staff, Integer> {
    public StaffDAO() {
        super(Staff.class);
    }

    @Override
    public void initializeLazyRelations(Staff obj) {
        AdultDAO parentEntityDAO = new AdultDAO();
        parentEntityDAO.initializeLazyRelations(obj);
    }
}