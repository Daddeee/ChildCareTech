package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class WorkDayDTOEntityAssembler implements AbstractDTOEntityAssembler<WorkDay, WorkDayDTO> {
    @Override
    public WorkDay assemble(WorkDayDTO dto) {
        WorkDay entity = getWorkDay(dto);
        if (entity == null) return null;

        Set<Meal> meals = new HashSet<>();
        for(MealDTO m : dto.getMeals())
            meals.add(MealDTOEntityAssembler.assembleWorkDayManySide(m, entity));
        entity.setMeals(meals);

        Set<Event> events = new HashSet<>();
        for(EventDTO e : dto.getEvents())
            events.add(EventDTOEntityAssembler.assembleWorkDayManySide(e, entity));
        entity.setEvents(events);

        return entity;
    }

    public static WorkDay assembleEventOneSide(WorkDayDTO dto){
        WorkDay entity = getWorkDay(dto);
        if (entity == null) return null;

        Set<Meal> meals = new HashSet<>();
        for(MealDTO m : dto.getMeals())
            meals.add(DTOEntityAssembler.getEntity(m));
        entity.setMeals(meals);

        return entity;
    }

    public static WorkDay assembleMealOneSide(WorkDayDTO dto){
        WorkDay entity = getWorkDay(dto);
        if (entity == null) return null;

        Set<Event> events = new HashSet<>();
        for(EventDTO e : dto.getEvents())
            events.add(EventDTOEntityAssembler.assembleWorkDayManySide(e, entity));
        entity.setEvents(events);

        return entity;
    }

    private static WorkDay getWorkDay(WorkDayDTO dto) {
        if(dto == null)
            return null;

        return new WorkDay(
                dto.getId(),
                dto.getDate(),
                dto.getEntryTime(),
                dto.getExitTime(),
                dto.isHoliday()
        );
    }
}
