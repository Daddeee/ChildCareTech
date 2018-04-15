package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.utils.DTO.DTOFactory;

public class CheckpointDTOFactory implements AbstractDTOFactory<Checkpoint, CheckpointDTO> {
    @Override
    public CheckpointDTO getDTO(Checkpoint entity) {
        return getEventDTO(entity, WorkDayDTOFactory.getEventOneSide(entity.getWorkDay()));
    }

    public static CheckpointDTO getWorkDayManySide(Checkpoint entity, WorkDayDTO workDayDTO){
        return getEventDTO(entity, workDayDTO);
    }

    private static CheckpointDTO getEventDTO(Checkpoint entity, WorkDayDTO workDayDTO) {
        if (entity == null)
            return null;

        return new CheckpointDTO(
                entity.getId(),
                workDayDTO,
                DTOFactory.getDTO(entity.getPerson()),
                entity.getTime(),
                entity.isIn()
        );
    }
}

