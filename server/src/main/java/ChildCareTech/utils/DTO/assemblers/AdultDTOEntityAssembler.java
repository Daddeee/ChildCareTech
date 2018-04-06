package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.model.adult.Adult;

public class AdultDTOEntityAssembler extends AbstractDTOEntityAssembler<Adult, AdultDTO> {
    @Override
    public Adult assembleWithoutRelations(AdultDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Adult entity, AdultDTO dto) {

    }
}
