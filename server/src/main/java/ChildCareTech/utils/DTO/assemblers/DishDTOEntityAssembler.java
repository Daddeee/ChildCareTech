package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.model.dish.Dish;

public class DishDTOEntityAssembler extends AbstractDTOEntityAssembler<Dish, DishDTO> {
    @Override
    public Dish assembleWithoutRelations(DishDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Dish entity, DishDTO dto) {

    }
}
