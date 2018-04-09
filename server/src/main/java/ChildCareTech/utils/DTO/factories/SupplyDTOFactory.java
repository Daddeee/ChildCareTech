package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.food.Food;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.utils.DTO.DTOFactory;

import java.time.LocalDate;

public class SupplyDTOFactory implements AbstractDTOFactory<Supply, SupplyDTO> {
    @Override
    public SupplyDTO getDTO(Supply entity) {
        if (entity == null)
            return null;

        return new SupplyDTO(
                SupplierDTOFactory.getSupplyOneSide(entity.getSupplier()),
                FoodDTOFactory.getSupplyOneSide(entity.getFood()),
                entity.getQuantity(),
                entity.getDate()
        );
    }

    public static SupplyDTO getFoodManySide(Supply entity, FoodDTO foodDTO){
        if (entity == null)
            return null;

        return new SupplyDTO(
                SupplierDTOFactory.getSupplyOneSide(entity.getSupplier()),
                foodDTO,
                entity.getQuantity(),
                entity.getDate()
        );
    }

    public static SupplyDTO getSupplierManySide(Supply entity, SupplierDTO supplierDTO){
        if (entity == null)
            return null;

        return new SupplyDTO(
                supplierDTO,
                FoodDTOFactory.getSupplyOneSide(entity.getFood()),
                entity.getQuantity(),
                entity.getDate()
        );
    }
}

