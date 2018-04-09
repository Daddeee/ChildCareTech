package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.food.Food;
import ChildCareTech.model.supplier.Supplier;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

public class SupplyDTOEntityAssembler implements AbstractDTOEntityAssembler<Supply, SupplyDTO> {
    @Override
    public Supply assemble(SupplyDTO dto) {
        if(dto == null)
            return null;

        return new Supply(
                SupplierDTOEntityAssembler.assembleSupplyOneSide(dto.getSupplier()),
                FoodDTOEntityAssembler.assembleSupplyOneSide(dto.getFood()),
                dto.getQuantity(),
                dto.getDate()
        );
    }

    public static Supply assembleFoodManySide(SupplyDTO dto, Food food) {
        if(dto == null)
            return null;

        return new Supply(
                SupplierDTOEntityAssembler.assembleSupplyOneSide(dto.getSupplier()),
                food,
                dto.getQuantity(),
                dto.getDate()
        );
    }

    public static Supply assembleSupplierManySide(SupplyDTO dto, Supplier supplier){
        if(dto == null)
            return null;

        return new Supply(
                supplier,
                FoodDTOEntityAssembler.assembleSupplyOneSide(dto.getFood()),
                dto.getQuantity(),
                dto.getDate()
        );
    }
}
