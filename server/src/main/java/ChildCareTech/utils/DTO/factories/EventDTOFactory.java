package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.entities.Event;
import ChildCareTech.utils.DTO.DTOFactory;

public class EventDTOFactory implements AbstractDTOFactory<Event, EventDTO> {
    @Override
    public EventDTO getDTO(Event entity) {
        if (entity == null)
            return null;

        return new EventDTO(
                WorkDayDTOFactory.getEventOneSide(entity.getWorkDay()),
                DTOFactory.getDTO(entity.getPerson()),
                entity.getTime(),
                entity.isIn()
        );
    }

    public static EventDTO getWorkDayManySide(Event entity, WorkDayDTO workDayDTO){
        if (entity == null)
            return null;

        return new EventDTO(
                workDayDTO,
                DTOFactory.getDTO(entity.getPerson()),
                entity.getTime(),
                entity.isIn()
        );
    }
}

