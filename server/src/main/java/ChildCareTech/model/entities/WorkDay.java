package ChildCareTech.model.entities;


import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a row of the WorkDay table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the WorkDay table in the database.
 */
@Entity
public class WorkDay implements iEntity<WorkDay, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private LocalDate date;

    private LocalTime entryTime;

    private LocalTime exitTime;

    private boolean isHoliday;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workDay")
    private Set<Meal> meals;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workDay")
    private Set<Event> events;

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public WorkDay() {}

    /**
     * Create a WorkDay entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param date the work day date.
     * @param entryTime the time of entry on this day.
     * @param exitTime the time of exit on this day.
     * @param isHoliday is this day a holiday?
     */
    public WorkDay(LocalDate date, LocalTime entryTime, LocalTime exitTime, boolean isHoliday) {
        this.date = date;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.isHoliday = isHoliday;
    }

    /**
     * Create a WorkDay entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param date the work day's date.
     * @param entryTime the time of entry on this day.
     * @param exitTime the time of exit on this day.
     * @param isHoliday is this day a holiday?
     */
    public WorkDay(int id, LocalDate date, LocalTime entryTime, LocalTime exitTime, boolean isHoliday) {
        this.id = id;
        this.date = date;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.isHoliday = isHoliday;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    /**
     * @return the entity's id.
     */
    public int getId() {
        return id;
    }

    /**
     * @return the work day's date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return the time of entry on this day.
     */
    public LocalTime getEntryTime() {
        return entryTime;
    }

    /**
     * @return the time of exit on this day.
     */
    public LocalTime getExitTime() {
        return exitTime;
    }

    /**
     * @return true if this is a holiday day, otherwise false.
     */
    public boolean isHoliday() {
        return isHoliday;
    }

    /**
     * @return a Set containing all the {@link Meal Meal(s)} associated to this WorkDay.
     */
    public Set<Meal> getMeals() {
        return meals == null ? Collections.EMPTY_SET : meals;
    }

    /**
     * @param meals a Set containing the {@link Meal Meal(s)} associated to this WorkDay.
     */
    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    /**
     * @return a Set containing all the {@link Event Event(s)} associated to this WorkDay.
     */
    public Set<Event> getEvents() {
        return events == null ? Collections.EMPTY_SET : events;
    }

    /**
     * @param events a Set containing the {@link Event Event(s)} associated to this WorkDay.
     */
    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkDay)) return false;
        return this.date.equals(((WorkDay) o).date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    private void setId(int id) {
        this.id = id;
    }
}
