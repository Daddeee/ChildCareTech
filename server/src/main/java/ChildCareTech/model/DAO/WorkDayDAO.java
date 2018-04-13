package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

import java.time.LocalDate;

public class WorkDayDAO extends AbstractGenericDAO<WorkDay, Integer> {
    public WorkDayDAO() {
        super(WorkDay.class);
    }

    public LocalDate getMinPersistentDate(){
        return session.createQuery("select min(date) from WorkDay", LocalDate.class).getSingleResult();
    }

    public LocalDate getMaxPersistentDate(){
        return session.createQuery("select max(date) from WorkDay", LocalDate.class).getSingleResult();
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