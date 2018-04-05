package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.model.supplier.Supplier;

public class SupplierDTOEntityAssembler extends AbstractDTOEntityAssembler<Supplier, SupplierDTO> {
    @Override
    public Supplier assembleWithoutRelations(SupplierDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Supplier entity, SupplierDTO dto) {

    }
}
