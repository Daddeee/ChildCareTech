package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.model.entities.Staff;
import ChildCareTech.utils.DTO.EntityFactoryFacade;

public class StaffEntityFactory implements AbstractEntityFactory<Staff, StaffDTO> {
    @Override
    public Staff assemble(StaffDTO dto) {
        if(dto == null)
            return null;

        return new Staff(
                dto.getId(),
                EntityFactoryFacade.getEntity(dto.getPerson())
        );
    }
}
