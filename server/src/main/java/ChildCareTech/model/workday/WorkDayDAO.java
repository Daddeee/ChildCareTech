package ChildCareTech.model.workday;

import ChildCareTech.utils.GenericDAO;

public class WorkDayDAO extends GenericDAO<WorkDay, Integer> {
    public WorkDayDAO() {
        super(WorkDay.class);
    }
}