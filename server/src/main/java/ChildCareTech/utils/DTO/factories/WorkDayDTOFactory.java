package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.event.Event;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class WorkDayDTOFactory implements AbstractDTOFactory<WorkDay, WorkDayDTO> {
    @Override
    public WorkDayDTO getDTO(WorkDay entity) {
        if(entity == null)
            return null;

        Set<MealDTO> meals = new HashSet<>();
        for(Meal m : entity.getMeals())
            meals.add(DTOFactory.getDTO(m));

        Set<EventDTO> events = new HashSet<>();
        for(Event e : entity.getEvents())
            events.add(DTOFactory.getDTO(e));

        return new WorkDayDTO(
                entity.getDate(),
                meals,
                events
        );
    }
}

