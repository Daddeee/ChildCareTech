package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.entities.Event;

public class EventDTOFactory implements AbstractDTOFactory<Event, EventDTO> {
    @Override
    public EventDTO getDTO(Event entity) {
        return getEventDTO(entity, WorkDayDTOFactory.getEventOneSide(entity.getWorkDay()));
    }

    public static EventDTO getWorkDayManySide(Event entity, WorkDayDTO workDayDTO){
        return getEventDTO(entity, workDayDTO);
    }

    private static EventDTO getEventDTO(Event entity, WorkDayDTO workDayDTO){
        if(entity == null)
            return null;

        return new EventDTO(
                entity.getId(),
                entity.getName(),
                workDayDTO,
                entity.getBeginTime(),
                entity.getEndTime(),
                entity.getEventStatus()
        );
    }
}
