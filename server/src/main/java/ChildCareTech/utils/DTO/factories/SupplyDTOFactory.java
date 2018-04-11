package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.entities.Supply;

public class SupplyDTOFactory implements AbstractDTOFactory<Supply, SupplyDTO> {
    @Override
    public SupplyDTO getDTO(Supply entity) {
        if(entity == null) return null;
        return getSupplyDTO(entity,
                SupplierDTOFactory.getSupplyOneSide(entity.getSupplier()),
                FoodDTOFactory.getSupplyOneSide(entity.getFood())
        );
    }

    public static SupplyDTO getFoodManySide(Supply entity, FoodDTO foodDTO){
        if(entity == null) return null;
        return getSupplyDTO(entity,
                SupplierDTOFactory.getSupplyOneSide(entity.getSupplier()),
                foodDTO
        );
    }

    public static SupplyDTO getSupplierManySide(Supply entity, SupplierDTO supplierDTO){
        if(entity == null) return null;
        return getSupplyDTO(entity,
                supplierDTO,
                FoodDTOFactory.getSupplyOneSide(entity.getFood())
        );
    }

    private static SupplyDTO getSupplyDTO(Supply entity, SupplierDTO supplierDTO, FoodDTO foodDTO) {
        if (entity == null)
            return null;

        return new SupplyDTO(
                entity.getId(),
                supplierDTO,
                foodDTO,
                entity.getQuantity(),
                entity.getDate()
        );
    }
}

