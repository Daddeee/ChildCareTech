package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.model.entities.Meal;

import java.util.HashSet;
import java.util.Set;

public class CanteenDTOEntityAssembler implements AbstractDTOEntityAssembler<Canteen, CanteenDTO> {
    @Override
    public Canteen assemble(CanteenDTO dto) {
        Canteen entity = getCanteen(dto);
        if(entity == null) return null;

        Set<Meal> meals = new HashSet<>();
        for(MealDTO m : dto.getMeals())
            meals.add(MealDTOEntityAssembler.assembleCanteenManySide(m, entity));
        entity.setMeals(meals);

        return entity;
    }

    public static Canteen assembleMealOneSide(CanteenDTO dto){
        return getCanteen(dto);
    }

    private static Canteen getCanteen(CanteenDTO dto) {
        if(dto==null)
            return null;
        return new Canteen(
                dto.getId(),
                dto.getName(),
                null
        );
    }
}
