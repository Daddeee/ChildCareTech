package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

public class MealDTOEntityAssembler implements AbstractDTOEntityAssembler<Meal, MealDTO> {
    @Override
    public Meal assemble(MealDTO dto) {
        if(dto == null)
            return null;

        return new Meal(
                DTOEntityAssembler.getEntity(dto.getCanteen()),
                dto.getMealNum(),
                DTOEntityAssembler.getEntity(dto.getWorkDay())
        );
    }
}
