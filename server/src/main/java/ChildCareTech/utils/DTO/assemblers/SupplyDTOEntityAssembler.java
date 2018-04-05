package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.supply.Supply;

public class SupplyDTOEntityAssembler extends AbstractDTOEntityAssembler<Supply, SupplyDTO> {
    @Override
    public Supply assembleWithoutRelations(SupplyDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Supply entity, SupplyDTO dto) {

    }
}
