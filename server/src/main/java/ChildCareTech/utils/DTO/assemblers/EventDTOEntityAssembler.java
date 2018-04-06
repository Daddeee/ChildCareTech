package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.model.event.Event;

public class EventDTOEntityAssembler extends AbstractDTOEntityAssembler<Event, EventDTO> {
    @Override
    public Event assembleWithoutRelations(EventDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Event entity, EventDTO dto) {

    }
}
