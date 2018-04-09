package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.dish.Dish;
import ChildCareTech.model.food.Food;
import ChildCareTech.model.supply.Supply;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class FoodDTOEntityAssembler implements AbstractDTOEntityAssembler<Food, FoodDTO> {
    @Override
    public Food assemble(FoodDTO dto) {
        if(dto == null)
            return null;

        Food entity = new Food(
                dto.getName(),
                dto.isDrink()
        );

        Set<Supply> supplies = new HashSet<>();
        for(SupplyDTO e : dto.getSupplies())
            supplies.add(DTOEntityAssembler.getEntity(e));
        entity.setSupplies(supplies);

        return entity;
    }
}
