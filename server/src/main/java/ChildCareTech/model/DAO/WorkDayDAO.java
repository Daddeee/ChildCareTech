package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;

/**
 * A Data Access Object that operates with WorkDay entities.
 */
public class WorkDayDAO extends AbstractGenericDAO<WorkDay, Integer> {
    public WorkDayDAO() {
        super(WorkDay.class);
    }

    /**
     * Read the following workday from the database.
     *
     * @param today the provided WorkDay entity.
     * @return the WorkDay with date following the provided date by 1 day (null if not present).
     */
    public WorkDay tomorrow(WorkDay today){
        List<WorkDay> result = read("date", today.getDate().plusDays(1));
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * @return the lowest date saved in the WorkDay table.
     */
    public LocalDate getMinPersistentDate(){
        return session.createQuery("select min(date) from WorkDay", LocalDate.class).getSingleResult();
    }

    /**
     * @return the greatest date saved in the WorkDay table.
     */
    public LocalDate getMaxPersistentDate(){
        return session.createQuery("select max(date) from WorkDay", LocalDate.class).getSingleResult();
    }

    @Override
    public void initializeLazyRelations(WorkDay workDay){
        initializeMealRelation(workDay);
        initializeEventRelation(workDay);
    }

    private void initializeMealRelation(WorkDay workDay){
        Hibernate.initialize(workDay.getMeals());
    }

    private void initializeEventRelation(WorkDay workDay){
        Hibernate.initialize(workDay.getEvents());
    }
}