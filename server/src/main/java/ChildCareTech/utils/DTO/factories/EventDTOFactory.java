package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.model.entities.Event;

import java.util.HashSet;
import java.util.Set;

public class EventDTOFactory implements AbstractDTOFactory<Event, EventDTO> {
    @Override
    public EventDTO getDTO(Event entity) {
        if(entity == null) return null;
        EventDTO dto =  getEventDTO(entity, WorkDayDTOFactory.getEventOneSide(entity.getWorkDay()));

        Set<CheckpointDTO> checkpoints = new HashSet<>();
        for(Checkpoint c : entity.getCheckpoints())
            checkpoints.add(CheckpointDTOFactory.getEventManySide(c, dto));
        dto.setCheckpoints(checkpoints);

        return dto;
    }

    public static EventDTO getWorkDayManySide(Event entity, WorkDayDTO workDayDTO){
        if(entity == null) return null;
        EventDTO dto =  getEventDTO(entity, workDayDTO);

        Set<CheckpointDTO> checkpoints = new HashSet<>();
        for(Checkpoint c : entity.getCheckpoints())
            checkpoints.add(CheckpointDTOFactory.getEventManySide(c, dto));
        dto.setCheckpoints(checkpoints);

        return dto;
    }

    public static EventDTO getCheckpointOneSide(Event entity){
        if(entity == null) return null;
        return getEventDTO(entity, WorkDayDTOFactory.getEventOneSide(entity.getWorkDay()));
    }

    private static EventDTO getEventDTO(Event entity, WorkDayDTO workDayDTO){
        if(entity == null)
            return null;

        return new EventDTO(
                entity.getId(),
                entity.getName(),
                workDayDTO,
                entity.getBeginTime(),
                entity.getEndTime(),
                entity.getEventStatus(),
                null
        );
    }
}
