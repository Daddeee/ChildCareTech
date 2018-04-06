package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.model.kid.Kid;

public class KidDTOEntityAssembler extends AbstractDTOEntityAssembler<Kid, KidDTO> {
    @Override
    public Kid assembleWithoutRelations(KidDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Kid entity, KidDTO dto) {

    }
}
