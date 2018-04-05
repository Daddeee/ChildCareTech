package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.model.food.Food;

public class FoodDTOEntityAssembler extends AbstractDTOEntityAssembler<Food, FoodDTO> {
    @Override
    public Food assembleWithoutRelations(FoodDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Food entity, FoodDTO dto) {

    }
}
