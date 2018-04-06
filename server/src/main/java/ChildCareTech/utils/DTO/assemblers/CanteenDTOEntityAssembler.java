package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.model.canteen.Canteen;

public class CanteenDTOEntityAssembler extends AbstractDTOEntityAssembler<Canteen, CanteenDTO> {
    @Override
    public Canteen assembleWithoutRelations(CanteenDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Canteen entity, CanteenDTO dto) {

    }
}
