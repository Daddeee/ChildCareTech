package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.model.entities.Canteen;
import ChildCareTech.model.entities.Meal;

import java.util.HashSet;
import java.util.Set;

public class CanteenDTOFactory implements AbstractDTOFactory<Canteen, CanteenDTO> {
    @Override
    public CanteenDTO getDTO(Canteen entity) {
        CanteenDTO dto = getCanteenDTO(entity);
        if (dto == null) return null;

        Set<MealDTO> meals = new HashSet<>();
        for (Meal m : entity.getMeals())
            meals.add(MealDTOFactory.getCanteenManySide(m, dto));
        dto.setMeals(meals);

        return dto;
    }

    public static CanteenDTO getMealOneSide(Canteen entity){
        return getCanteenDTO(entity);
    }

    private static CanteenDTO getCanteenDTO(Canteen entity) {
        if (entity == null)
            return null;

        CanteenDTO dto = new CanteenDTO(
                entity.getId(),
                entity.getName(),
                null
        );
        return dto;
    }
}

