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
        if(dto == null)
            return null;

        Canteen entity = new Canteen(
                dto.getName()
        );

        Set<Meal> meals = new HashSet<>();
        for(MealDTO m : dto.getMeals())
            meals.add(MealDTOEntityAssembler.assembleCanteenManySide(m, entity));
        entity.setMeals(meals);

        return entity;
    }

    public static Canteen assembleMealOneSide(CanteenDTO dto){
        if(dto==null)
            return null;
        return new Canteen(dto.getName());
    }
}
