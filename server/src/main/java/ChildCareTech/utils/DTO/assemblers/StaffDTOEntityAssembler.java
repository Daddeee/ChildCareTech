package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.model.entities.Staff;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

public class StaffDTOEntityAssembler implements AbstractDTOEntityAssembler<Staff, StaffDTO> {
    @Override
    public Staff assemble(StaffDTO dto) {
        if(dto == null)
            return null;

        return new Staff(
                DTOEntityAssembler.getEntity(dto.getPerson())
        );
    }
}
