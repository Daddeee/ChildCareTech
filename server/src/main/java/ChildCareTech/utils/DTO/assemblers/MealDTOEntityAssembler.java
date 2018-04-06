package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.model.meal.Meal;

public class MealDTOEntityAssembler extends AbstractDTOEntityAssembler<Meal, MealDTO> {
    @Override
    public Meal assembleWithoutRelations(MealDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Meal entity, MealDTO dto) {

    }
}
