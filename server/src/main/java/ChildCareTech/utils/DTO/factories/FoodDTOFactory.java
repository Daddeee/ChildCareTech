package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.dish.Dish;
import ChildCareTech.model.food.Food;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class FoodDTOFactory implements AbstractDTOFactory<Food, FoodDTO> {
    @Override
    public FoodDTO getDTO(Food entity) {
        if (entity == null)
            return null;

        String name = entity.getName();
        boolean isDrink = entity.isDrink();
        int residualQuantity = entity.getResidualQuantity();

        Set<SupplyDTO> supplies = new HashSet<>();
        for (Supply s : entity.getSupplies())
            supplies.add(DTOFactory.getDTO(s));

        return new FoodDTO(name, isDrink, residualQuantity, supplies);
    }
}

