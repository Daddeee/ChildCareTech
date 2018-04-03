package ChildCareTech.model.meal;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.canteen.CanteenDTOImpl;
import ChildCareTech.model.workday.WorkDayDTOImpl;

public class MealDTOImpl implements MealDTO {
    private CanteenDTO canteen;
    private WorkDayDTO workDay;
    private int mealNum;

    public MealDTOImpl(Meal meal) {
        canteen = new CanteenDTOImpl(meal.getCanteen());
        workDay = new WorkDayDTOImpl(meal.getWorkDay());
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