package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.model.staff.Staff;

public class StaffDTOEntityAssembler extends AbstractDTOEntityAssembler<Staff, StaffDTO> {
    @Override
    public Staff assembleWithoutRelations(StaffDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Staff entity, StaffDTO dto) {

    }
}
