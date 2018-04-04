package ChildCareTech.common.DTO;

import ChildCareTech.model.event.Event;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.utils.DTO.DTOFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class WorkDayDTO implements Serializable{
    private LocalDate date;
    private Set<MealDTO> meals;
    private Set<EventDTO> events;

    public WorkDayDTO(LocalDate date, Set<MealDTO> meals, Set<EventDTO> events){
        this.date = date;
        this.meals = meals;
        this.events = events;
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