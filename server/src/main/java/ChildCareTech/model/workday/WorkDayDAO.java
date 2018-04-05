package ChildCareTech.model.workday;

import ChildCareTech.utils.AbstractGenericDAO;

public class WorkDayDAO extends AbstractGenericDAO<WorkDay, Integer> {
    public WorkDayDAO() {
        super(WorkDay.class);
    }
}