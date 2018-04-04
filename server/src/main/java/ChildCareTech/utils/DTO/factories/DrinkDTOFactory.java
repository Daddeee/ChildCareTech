package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.DrinkDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.drink.Drink;
import ChildCareTech.model.food.Food;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class DrinkDTOFactory implements AbstractDTOFactory<Drink, DrinkDTO> {
    @Override
    public DrinkDTO getDTO(Drink entity) {
        if(entity == null)
            return null;

        String name = entity.getName();
        MenuDTO menu = DTOFactory.getDTO(entity.getMenu());

        Set<FoodDTO> foods = new HashSet<>();
        for(Food f : entity.getFoods())
            foods.add(DTOFactory.getDTO(f));

        return new DrinkDTO(name, menu, foods);
    }
}

