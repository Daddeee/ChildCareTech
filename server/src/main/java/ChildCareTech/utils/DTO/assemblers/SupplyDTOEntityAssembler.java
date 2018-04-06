package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

public class SupplyDTOEntityAssembler implements AbstractDTOEntityAssembler<Supply, SupplyDTO> {
    @Override
    public Supply assemble(SupplyDTO dto) {
        if(dto == null)
            return null;

        return new Supply(
                DTOEntityAssembler.getEntity(dto.getSupplier()),
                DTOEntityAssembler.getEntity(dto.getFood()),
                dto.getQuantity(),
                dto.getDate()
        );
    }
}
