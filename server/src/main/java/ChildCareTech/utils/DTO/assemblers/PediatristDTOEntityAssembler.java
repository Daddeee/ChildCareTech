package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.model.pediatrist.Pediatrist;

public class PediatristDTOEntityAssembler extends AbstractDTOEntityAssembler<Pediatrist, PediatristDTO> {
    @Override
    public Pediatrist assembleWithoutRelations(PediatristDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Pediatrist entity, PediatristDTO dto) {

    }
}
