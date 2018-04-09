package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.event.Event;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.utils.DTO.DTOFactory;

import java.time.LocalTime;

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

