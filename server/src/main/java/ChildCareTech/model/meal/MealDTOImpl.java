package ChildCareTech.model.meal;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.utils.DTOFactory;

public class MealDTOImpl implements MealDTO {
    private CanteenDTO canteen;
    private WorkDayDTO workDay;
    private int mealNum;

    public MealDTOImpl(Meal meal) {
        canteen = DTOFactory.getDTO(meal.getCanteen());
        workDay = DTOFactory.getDTO(meal.getWorkDay());
        mealNum = meal.getMealNum();
    }

    @Override
    public CanteenDTO getCanteen() {
        return canteen;
    }

    @Override
    public WorkDayDTO getWorkDay() {
        return workDay;
    }

    @Override
    public int getMealNum() {
        return mealNum;
    }
}