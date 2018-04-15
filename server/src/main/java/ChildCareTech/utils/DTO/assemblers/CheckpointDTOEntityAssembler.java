package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.model.entities.Checkpoint;
import ChildCareTech.model.entities.WorkDay;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

public class CheckpointDTOEntityAssembler implements AbstractDTOEntityAssembler<Checkpoint, CheckpointDTO> {
    @Override
    public Checkpoint assemble(CheckpointDTO dto) {
        if(dto == null) return null;
        return getEvent(dto, WorkDayDTOEntityAssembler.assembleEventOneSide(dto.getWorkDay()));
    }

    public static Checkpoint assembleWorkDayManySide(CheckpointDTO dto, WorkDay workDay) {
        return getEvent(dto, workDay);
    }

    private static Checkpoint getEvent(CheckpointDTO dto, WorkDay workDay) {
        if(dto == null)
            return null;

        return new Checkpoint(
                dto.getId(),
                workDay,
                DTOEntityAssembler.getEntity(dto.getPerson()),
                dto.getTime(),
                dto.isIn()
        );
    }
}
