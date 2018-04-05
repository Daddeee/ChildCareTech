package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.model.bus.Bus;

public class BusDTOEntityAssembler extends AbstractDTOEntityAssembler<Bus, BusDTO> {
    @Override
    public Bus assembleWithoutRelations(BusDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Bus entity, BusDTO dto) {

    }
}
