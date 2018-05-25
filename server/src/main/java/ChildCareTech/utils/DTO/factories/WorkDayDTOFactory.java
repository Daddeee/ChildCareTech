package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.Meal;
import ChildCareTech.model.entities.WorkDay;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

public class WorkDayDTOFactory implements AbstractDTOFactory<WorkDay, WorkDayDTO> {
    @Override
    public WorkDayDTO getDTO(WorkDay entity) {
        WorkDayDTO dto = getWorkDayDTO(entity);
        if (dto == null) return null;

        loadMealRelationship(entity, dto);
        loadEventRelationship(entity, dto);

        return dto;
    }

    public static WorkDayDTO getMealOneSide(WorkDay entity){
        WorkDayDTO dto = getWorkDayDTO(entity);
        if (dto == null) return null;

        loadEventRelationship(entity, dto);

        return dto;
    }

    public static WorkDayDTO getEventOneSide(WorkDay entity){
        WorkDayDTO dto = getWorkDayDTO(entity);
        if (dto == null) return null;

        loadMealRelationship(entity, dto);

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

    private static void loadEventRelationship(WorkDay entity, WorkDayDTO dto) {
        Set<EventDTO> events = new HashSet<>();

        if(Hibernate.isInitialized(entity.getEvents()))
            for (Event e : entity.getEvents())
                events.add(EventDTOFactory.getWorkDayManySide(e, dto));

        dto.setEvents(events);
    }

    private static void loadMealRelationship(WorkDay entity, WorkDayDTO dto) {
        Set<MealDTO> meals = new HashSet<>();

        if(Hibernate.isInitialized(entity.getMeals()))
            for (Meal m : entity.getMeals())
                meals.add(MealDTOFactory.getWorkDayManySide(m, dto));

        dto.setMeals(meals);
    }
}

