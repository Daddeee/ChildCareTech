package ChildCareTech.common.DTO;

import ChildCareTech.model.drink.Drink;
import ChildCareTech.model.food.Food;
import ChildCareTech.utils.DTO.DTOFactory;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DrinkDTO implements Serializable {
    private String name;
    private MenuDTO menu;
    private Set<FoodDTO> foods;

    public DrinkDTO(String name, MenuDTO menu, Set<FoodDTO> foods){
        this.name = name;
        this.menu = menu;
        this.foods = foods;
    }

    public String getName() {
        return name;
    }

    public MenuDTO getMenu() {
        return menu;
    }

    public Set<FoodDTO> getFoods() {
        return foods;
    }
}