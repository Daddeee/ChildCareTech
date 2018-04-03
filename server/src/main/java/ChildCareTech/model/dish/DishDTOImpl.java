package ChildCareTech.model.dish;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.FoodDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.food.Food;
import ChildCareTech.model.food.FoodDTOImpl;
import ChildCareTech.model.menu.MenuDTOImpl;

import java.util.HashSet;
import java.util.Set;

public class DishDTOImpl implements DishDTO {
    private String name;
    private MenuDTO menu;
    private Set<FoodDTO> foods;

    public DishDTOImpl(Dish dish){
        name = dish.getName();
        menu = new MenuDTOImpl(dish.getMenu());
        foods = new HashSet<>();
        for(Food f : dish.getFoods())
            foods.add(new FoodDTOImpl(f));
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