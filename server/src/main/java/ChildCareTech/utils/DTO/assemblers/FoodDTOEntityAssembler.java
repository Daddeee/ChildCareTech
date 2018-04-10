package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.SupplyDTO;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Supply;

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
            supplies.add(SupplyDTOEntityAssembler.assembleFoodManySide(e, entity));
        entity.setSupplies(supplies);

        return entity;
    }

    public static Food assembleSupplyOneSide(FoodDTO dto){
        if(dto == null)
            return null;

        return new Food(
                dto.getName(),
                dto.isDrink()
        );
    }
}
