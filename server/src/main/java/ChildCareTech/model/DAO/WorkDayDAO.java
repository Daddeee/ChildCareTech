package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class WorkDayDAO extends AbstractGenericDAO<WorkDay, Integer> {
    public WorkDayDAO() {
        super(WorkDay.class);
    }

    @Override
    public void initializeLazyRelations(WorkDay workDay){
        initializeMealRelation(workDay);
        initializeEventRelation(workDay);
    }

    public void initializeMealRelation(WorkDay workDay){
        Hibernate.initialize(workDay.getMeals());
    }

    public void initializeEventRelation(WorkDay workDay){
        Hibernate.initialize(workDay.getEvents());
    }
}