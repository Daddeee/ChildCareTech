package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class MenuDTO implements Serializable {
    private MealDTO meal;
    private int numMenu;
    private Set<DishDTO> dishes;
    private DrinkDTO drink;

    public MenuDTO(MealDTO meal, int numMenu, Set<DishDTO> dishes, DrinkDTO drink) {
        this.meal = meal;
        this.numMenu = numMenu;
        this.dishes = dishes == null ? Collections.EMPTY_SET : dishes;
        this.drink = drink;
    }

    public MealDTO getMeal() {
        return meal;
    }

    public void setMeal(MealDTO meal) {
        this.meal = meal;
    }

    public int getNumMenu() {
        return numMenu;
    }

    public void setNumMenu(int numMenu) {
        this.numMenu = numMenu;
    }

    public Set<DishDTO> getDishes() {
        return dishes;
    }

    public void setDishes(Set<DishDTO> dishes) {
        this.dishes = dishes;
    }

    public DrinkDTO getDrink() {
        return drink;
    }

    public void setDrink(DrinkDTO drink) {
        this.drink = drink;
    }
}