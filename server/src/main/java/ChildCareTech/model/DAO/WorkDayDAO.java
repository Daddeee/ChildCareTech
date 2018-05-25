package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;

public class WorkDayDAO extends AbstractGenericDAO<WorkDay, Integer> {
    public WorkDayDAO() {
        super(WorkDay.class);
    }

    public WorkDay tomorrow(WorkDay today){
        List<WorkDay> result = read("date", today.getDate().plusDays(1));
        return result.isEmpty() ? null : result.get(0);
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