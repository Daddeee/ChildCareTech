package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Supply;

import java.util.HashSet;
import java.util.Set;

public class FoodDTOFactory implements AbstractDTOFactory<Food, FoodDTO> {
    @Override
    public FoodDTO getDTO(Food entity) {
        FoodDTO dto = getFoodDTO(entity);
        if(dto == null) return null;

        Set<SupplyDTO> supplies = new HashSet<>();
        for (Supply s : entity.getSupplies())
            supplies.add(SupplyDTOFactory.getFoodManySide(s, dto));
        dto.setSupplies(supplies);

        return dto;
    }

    public static FoodDTO getSupplyOneSide(Food entity){
        return getFoodDTO(entity);
    }

    private static FoodDTO getFoodDTO(Food entity) {
        if (entity == null)
            return null;

        return new FoodDTO(
                entity.getId(),
                entity.getName(),
                entity.isDrink(),
                entity.getResidualQuantity(),
                null
        );
    }
}

