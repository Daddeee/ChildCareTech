package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;
/**
 * This class provides a Data Transfer Object encapsulation for {@link ChildCareTech.model.entities.WorkDay WorkDay} entity.
 */
public class WorkDayDTO implements Serializable {
    private int id;
    private LocalDate date;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private boolean isHoliday;
    private Set<MealDTO> meals;
    private Set<EventDTO> events;

    public WorkDayDTO(int id, LocalDate date, LocalTime entryTime, LocalTime exitTime, boolean isHoliday, Set<MealDTO> meals, Set<EventDTO> events) {
        this.id = id;
        this.date = date;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.isHoliday = isHoliday;
        this.meals = meals == null ? Collections.EMPTY_SET : meals;
        this.events = events == null ? Collections.EMPTY_SET : events;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    public Set<MealDTO> getMeals() {
        return meals;
    }

    public void setMeals(Set<MealDTO> meals) {
        this.meals = meals;
    }

    public Set<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(Set<EventDTO> events) {
        this.events = events;
    }
}