package ChildCareTech.model.workday;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.event.Event;
import ChildCareTech.model.event.EventDTOImpl;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.model.meal.MealDTOImpl;
import ChildCareTech.utils.DTOFactory;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class WorkDayDTOImpl implements WorkDayDTO {
    private LocalDate date;
    private Set<MealDTO> meals;
    private Set<EventDTO> events;

    public WorkDayDTOImpl(WorkDay workDay){
        date = workDay.getDate();

        meals = new HashSet<>();
        for(Meal m : workDay.getMeals())
            meals.add(DTOFactory.getDTO(m));

        events = new HashSet<>();
        for(Event e : workDay.getEvents())
            events.add(DTOFactory.getDTO(e));
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public Set<MealDTO> getMeals() {
        return meals;
    }

    @Override
    public Set<EventDTO> getEvents() {
        return events;
    }
}