package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.utils.DTO.DTOFactory;

public class MealDTOFactory implements AbstractDTOFactory<Meal, MealDTO> {
    @Override
    public MealDTO getDTO(Meal entity) {
        if (entity == null)
            return null;

        CanteenDTO canteen = DTOFactory.getDTO(entity.getCanteen());
        WorkDayDTO workDay = DTOFactory.getDTO(entity.getWorkDay());
        int mealNum = entity.getMealNum();

        return new MealDTO(canteen, workDay, mealNum);
    }
}

