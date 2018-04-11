package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Supplier;
import ChildCareTech.model.entities.Supply;

public class SupplyDTOEntityAssembler implements AbstractDTOEntityAssembler<Supply, SupplyDTO> {
    @Override
    public Supply assemble(SupplyDTO dto) {
        if(dto == null) return null;
        return getSupply(
                dto,
                SupplierDTOEntityAssembler.assembleSupplyOneSide(dto.getSupplier()),
                FoodDTOEntityAssembler.assembleSupplyOneSide(dto.getFood())
        );
    }

    public static Supply assembleFoodManySide(SupplyDTO dto, Food food) {
        if(dto == null) return null;
        return getSupply(
                dto,
                SupplierDTOEntityAssembler.assembleSupplyOneSide(dto.getSupplier()),
                food
        );
    }

    public static Supply assembleSupplierManySide(SupplyDTO dto, Supplier supplier){
        if(dto == null) return null;
        return getSupply(
                dto,
                supplier,
                FoodDTOEntityAssembler.assembleSupplyOneSide(dto.getFood())
        );
    }

    private static Supply getSupply(SupplyDTO dto, Supplier supplier, Food food) {
        if(dto == null)
            return null;

        return new Supply(
                dto.getId(),
                supplier,
                food,
                dto.getQuantity(),
                dto.getDate()
        );
    }
}
