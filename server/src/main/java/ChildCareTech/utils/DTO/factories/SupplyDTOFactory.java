package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.utils.DTO.DTOFactory;

import java.time.LocalDate;

public class SupplyDTOFactory implements AbstractDTOFactory<Supply, SupplyDTO> {
    @Override
    public SupplyDTO getDTO(Supply entity) {
        if(entity == null)
            return null;

        SupplierDTO supplier = DTOFactory.getDTO(entity.getSupplier());
        FoodDTO food = DTOFactory.getDTO(entity.getFood());
        int quantity = entity.getQuantity();
        LocalDate date = entity.getDate();

        return new SupplyDTO(supplier, food, quantity, date);
    }
}

