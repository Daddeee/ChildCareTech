package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

public class WorkDayDTO implements Serializable {
    private LocalDate date;
    private Set<MealDTO> meals;
    private Set<EventDTO> events;

    public WorkDayDTO(LocalDate date, Set<MealDTO> meals, Set<EventDTO> events) {
        this.date = date;
        this.meals = meals == null ? Collections.EMPTY_SET : meals;
        this.events = events == null ? Collections.EMPTY_SET : events;
    }

    public LocalDate getDate() {
        return date;
    }

    public Set<MealDTO> getMeals() {
        return meals;
    }

    public Set<EventDTO> getEvents() {
        return events;
    }
}