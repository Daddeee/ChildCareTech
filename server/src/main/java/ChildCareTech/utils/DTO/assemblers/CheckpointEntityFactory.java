package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.model.entities.Event;
import ChildCareTech.utils.DTO.EntityFactoryFacade;

public class CheckpointEntityFactory implements AbstractEntityFactory<Checkpoint, CheckpointDTO> {
    @Override
    public Checkpoint assemble(CheckpointDTO dto) {
        if(dto == null) return null;
        return getEvent(dto, EventEntityFactory.assembleCheckpointOneSide(dto.getEvent()));
    }

    public static Checkpoint assembleEventManySide(CheckpointDTO dto, Event event) {
        return getEvent(dto, event);
    }

    private static Checkpoint getEvent(CheckpointDTO dto, Event event) {
        if(dto == null)
            return null;

        return new Checkpoint(
                dto.getId(),
                event,
                EntityFactoryFacade.getEntity(dto.getPerson()),
                dto.getTime()
        );
    }
}
