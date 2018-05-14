package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.model.entities.Event;
import ChildCareTech.model.entities.WorkDay;

import java.util.HashSet;
import java.util.Set;

public class EventDTOEntityAssembler implements AbstractDTOEntityAssembler<Event, EventDTO> {
    @Override
    public Event assemble(EventDTO dto) {
        if(dto == null) return null;
        Event entity = getEvent(dto, WorkDayDTOEntityAssembler.assembleEventOneSide(dto.getWorkDay()));

        assembleCheckpointRelationship(dto, entity);

        return entity;
    }

    public static Event assembleWorkDayManySide(EventDTO dto, WorkDay workDay){
        if(dto == null) return null;
        Event entity = getEvent(dto, workDay);

        assembleCheckpointRelationship(dto, entity);

        return entity;
    }

    public static Event assembleCheckpointOneSide(EventDTO dto){
        if(dto == null) return null;
        return getEvent(dto, WorkDayDTOEntityAssembler.assembleEventOneSide(dto.getWorkDay()));
    }

    public static Event assembleMealOneSide(EventDTO dto, WorkDay workDay){
        if(dto == null) return null;
        Event entity = getEvent(dto, workDay);

        assembleCheckpointRelationship(dto, entity);

        return entity;
    }

    private static void assembleCheckpointRelationship(EventDTO dto, Event entity) {
        Set<Checkpoint> checkpoints = new HashSet<>();
        for(CheckpointDTO c : dto.getCheckpoints())
            checkpoints.add(CheckpointDTOEntityAssembler.assembleEventManySide(c, entity));
        entity.setCheckpoints(checkpoints);
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
