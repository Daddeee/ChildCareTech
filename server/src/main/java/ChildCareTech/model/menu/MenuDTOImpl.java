package ChildCareTech.model.menu;

import ChildCareTech.common.DTO.DishDTO;
import ChildCareTech.common.DTO.DrinkDTO;
import ChildCareTech.common.DTO.MealDTO;
import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.dish.Dish;
import ChildCareTech.model.dish.DishDTOImpl;
import ChildCareTech.model.drink.DrinkDTOImpl;
import ChildCareTech.model.meal.MealDTOImpl;
import ChildCareTech.utils.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class MenuDTOImpl implements MenuDTO {
    private MealDTO meal;
    private int numMenu;
    private Set<DishDTO> dishes;
    private DrinkDTO drink;

    public MenuDTOImpl(Menu menu){
        meal = DTOFactory.getDTO(menu.getMeal());
        numMenu = menu.getNumMenu();

        dishes = new HashSet<>();
        for(Dish d : menu.getDishes())
            dishes.add(DTOFactory.getDTO(d));

        drink = DTOFactory.getDTO(menu.getDrink());
    }

    @Override
    public MealDTO getMeal() {
        return meal;
    }

    @Override
    public int getNumMenu() {
        return numMenu;
    }

    @Override
    public Set<DishDTO> getDishes() {
        return dishes;
    }

    @Override
    public DrinkDTO getDrink() {
        return drink;
    }
}