package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.event.Event;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.utils.DTO.DTOFactory;
import ChildCareTech.utils.DTO.assemblers.MealDTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class WorkDayDTOFactory implements AbstractDTOFactory<WorkDay, WorkDayDTO> {
    @Override
    public WorkDayDTO getDTO(WorkDay entity) {
        if (entity == null)
            return null;

        WorkDayDTO dto = new WorkDayDTO(
                entity.getDate(),
                null,
                null
        );

        Set<MealDTO> meals = new HashSet<>();
        for (Meal m : entity.getMeals())
            meals.add(MealDTOFactory.getWorkDayManySide(m, dto));
        dto.setMeals(meals);

        Set<EventDTO> events = new HashSet<>();
        for (Event e : entity.getEvents())
            events.add(EventDTOFactory.getWorkDayManySide(e, dto));
        dto.setEvents(events);

        return dto;
    }

    public static WorkDayDTO getMealOneSide(WorkDay entity){
        if (entity == null)
            return null;

        WorkDayDTO dto = new WorkDayDTO(
                entity.getDate(),
                null,
                null
        );

        Set<EventDTO> events = new HashSet<>();
        for (Event e : entity.getEvents())
            events.add(EventDTOFactory.getWorkDayManySide(e, dto));
        dto.setEvents(events);

        return dto;
    }

    public static WorkDayDTO getEventOneSide(WorkDay entity){
        if (entity == null)
            return null;

        WorkDayDTO dto = new WorkDayDTO(
                entity.getDate(),
                null,
                null
        );

        Set<MealDTO> meals = new HashSet<>();
        for (Meal m : entity.getMeals())
            meals.add(DTOFactory.getDTO(m));
        dto.setMeals(meals);

        return dto;
    }
}

