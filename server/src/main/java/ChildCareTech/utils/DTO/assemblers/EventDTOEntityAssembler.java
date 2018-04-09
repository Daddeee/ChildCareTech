package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.model.event.Event;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

public class EventDTOEntityAssembler implements AbstractDTOEntityAssembler<Event, EventDTO> {
    @Override
    public Event assemble(EventDTO dto) {
        if(dto == null)
            return null;

        return new Event(
                WorkDayDTOEntityAssembler.assembleEventOneSide(dto.getWorkDay()),
                DTOEntityAssembler.getEntity(dto.getPerson()),
                dto.getTime(),
                dto.isIn()
        );
    }

    public static Event assembleWorkDayManySide(EventDTO dto, WorkDay workDay) {
        if(dto == null)
            return null;

        return new Event(
                workDay,
                DTOEntityAssembler.getEntity(dto.getPerson()),
                dto.getTime(),
                dto.isIn()
        );
    }
}
