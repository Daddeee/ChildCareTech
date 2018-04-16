package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.model.entities.Event;

public class EventDTOFactory implements AbstractDTOFactory<Event, EventDTO> {
    @Override
    public EventDTO getDTO(Event entity) {
        EventDTO dto = getEventDTO(entity);
        if(dto == null)return null;

        return dto;
    }

    private EventDTO getEventDTO(Event entity){
        if(entity == null)
            return null;

        return new EventDTO(
                entity.getId(),
                entity.getName(),
                WorkDayDTOFactory.getEventOneSide(entity.getWorkDay()),
                entity.getBeginTime(),
                entity.getEndTime(),
                entity.getEventStatus()
        );
    }
}
