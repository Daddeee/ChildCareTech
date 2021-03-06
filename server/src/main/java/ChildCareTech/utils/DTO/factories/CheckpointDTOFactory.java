package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.utils.DTO.DTOFactoryFacade;

public class CheckpointDTOFactory implements AbstractDTOFactory<Checkpoint, CheckpointDTO> {
    @Override
    public CheckpointDTO getDTO(Checkpoint entity) {
        return getEventDTO(entity, EventDTOFactory.getCheckpointOneSide(entity.getEvent()));
    }

    public static CheckpointDTO getEventManySide(Checkpoint entity, EventDTO eventDTO){
        return getEventDTO(entity, eventDTO);
    }

    private static CheckpointDTO getEventDTO(Checkpoint entity, EventDTO eventDTO) {
        if (entity == null)
            return null;

        return new CheckpointDTO(
                entity.getId(),
                eventDTO,
                DTOFactoryFacade.getDTO(entity.getPerson()),
                entity.getTime()
        );
    }
}

