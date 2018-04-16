package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.model.entities.Event;

public class EventDTOEntityAssembler implements AbstractDTOEntityAssembler<Event, EventDTO> {
    @Override
    public Event assemble(EventDTO dto) {
        Event entity = getEvent(dto);
        if(entity==null) return null;

        return entity;
    }

    private Event getEvent(EventDTO dto){
        if(dto==null)
            return null;

        return new Event(
                dto.getId(),
                dto.getName(),
                WorkDayDTOEntityAssembler.assembleEventOneSide(dto.getWorkDay()),
                dto.getBeginTime(),
                dto.getEndTime(),
                dto.getEventStatus()
        );
    }
}
