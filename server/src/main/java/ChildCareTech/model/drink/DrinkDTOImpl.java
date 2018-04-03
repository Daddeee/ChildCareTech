package ChildCareTech.model.drink;

import ChildCareTech.common.DTO.DrinkDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.food.Food;
import ChildCareTech.model.food.FoodDTOImpl;
import ChildCareTech.model.menu.MenuDTOImpl;
import ChildCareTech.utils.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class DrinkDTOImpl implements DrinkDTO {
    private String name;
    private MenuDTO menu;
    private Set<FoodDTO> foods;

    public DrinkDTOImpl(Drink drink){
        name = drink.getName();
        menu = DTOFactory.getDTO(drink.getMenu());
        foods = new HashSet<>();
        for(Food f : drink.getFoods())
            foods.add(DTOFactory.getDTO(f));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public MenuDTO getMenu() {
        return menu;
    }

    @Override
    public Set<FoodDTO> getFoods() {
        return foods;
    }
}