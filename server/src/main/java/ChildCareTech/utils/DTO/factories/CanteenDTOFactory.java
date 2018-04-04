package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.CanteenDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.model.canteen.Canteen;
import ChildCareTech.model.meal.Meal;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class CanteenDTOFactory implements AbstractDTOFactory<Canteen, CanteenDTO> {
    @Override
    public CanteenDTO getDTO(Canteen entity) {
        if(entity == null)
            return null;

        String name = entity.getName();

        Set<MealDTO> meals = new HashSet<>();
        for(Meal m : entity.getMeals())
            meals.add(DTOFactory.getDTO(m));

        return new CanteenDTO(name, meals);
    }
}

