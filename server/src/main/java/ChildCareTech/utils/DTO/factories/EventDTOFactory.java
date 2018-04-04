package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.event.Event;
import ChildCareTech.utils.DTO.DTOFactory;

import java.time.LocalTime;

public class EventDTOFactory implements AbstractDTOFactory<Event, EventDTO> {
    @Override
    public EventDTO getDTO(Event entity) {
        if (entity == null)
            return null;

        WorkDayDTO workDay = DTOFactory.getDTO(entity.getWorkDay());
        PersonDTO person = DTOFactory.getDTO(entity.getPerson());
        LocalTime time = entity.getTime();
        boolean isIn = entity.isIn();

        return new EventDTO(workDay, person, time, isIn);
    }
}

