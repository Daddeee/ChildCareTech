package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class WorkDayDTOFactory implements AbstractDTOFactory<WorkDay, WorkDayDTO> {
    @Override
    public WorkDayDTO getDTO(WorkDay entity) {
        WorkDayDTO dto = getWorkDayDTO(entity);
        if (dto == null) return null;

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
        WorkDayDTO dto = getWorkDayDTO(entity);
        if (dto == null) return null;

        Set<EventDTO> events = new HashSet<>();
        for (Event e : entity.getEvents())
            events.add(EventDTOFactory.getWorkDayManySide(e, dto));
        dto.setEvents(events);

        return dto;
    }

    public static WorkDayDTO getEventOneSide(WorkDay entity){
        WorkDayDTO dto = getWorkDayDTO(entity);
        if (dto == null) return null;

        Set<MealDTO> meals = new HashSet<>();
        for (Meal m : entity.getMeals())
            meals.add(DTOFactory.getDTO(m));
        dto.setMeals(meals);

        return dto;
    }

    private static WorkDayDTO getWorkDayDTO(WorkDay entity) {
        if (entity == null)
            return null;

        WorkDayDTO dto = new WorkDayDTO(
                entity.getId(),
                entity.getDate(),
                entity.getEntryTime(),
                entity.getExitTime(),
                entity.isHoliday(),
                null,
                null
        );
        return dto;
    }
}

