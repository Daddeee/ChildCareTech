package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.model.event.Event;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

public class EventDTOEntityAssembler implements AbstractDTOEntityAssembler<Event, EventDTO> {
    @Override
    public Event assemble(EventDTO dto) {
        if(dto == null)
            return null;

        return new Event(
                DTOEntityAssembler.getEntity(dto.getWorkDay()),
                DTOEntityAssembler.getEntity(dto.getPerson()),
                dto.getTime(),
                dto.isIn()
        );
    }
}
