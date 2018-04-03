package ChildCareTech.model.canteen;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.utils.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class CanteenDTOImpl implements CanteenDTO {
    private String name;
    private Set<MealDTO> meals;

    public CanteenDTOImpl(Canteen canteen) {
        name = canteen.getName();
        meals = new HashSet<>();
        for (Meal m : canteen.getMeals())
            meals.add(DTOFactory.getDTO(m));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<MealDTO> getMeals() {
        return meals;
    }
}