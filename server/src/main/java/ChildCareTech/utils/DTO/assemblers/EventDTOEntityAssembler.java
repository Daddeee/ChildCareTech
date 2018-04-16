package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.WorkDay;

public class EventDTOEntityAssembler implements AbstractDTOEntityAssembler<Event, EventDTO> {
    @Override
    public Event assemble(EventDTO dto) {
        return getEvent(dto, WorkDayDTOEntityAssembler.assembleEventOneSide(dto.getWorkDay()));
    }

    public static Event assembleWorkDayManySide(EventDTO dto, WorkDay workDay){
        return getEvent(dto, workDay);
    }

    private static Event getEvent(EventDTO dto, WorkDay workDay){
        if(dto==null)
            return null;

        return new Event(
                dto.getId(),
                dto.getName(),
                workDay,
                dto.getBeginTime(),
                dto.getEndTime(),
                dto.getEventStatus()
        );
    }
}
