package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * This class provides a Data Transfer Object encapsulation for {@link ChildCareTech.model.entities.Menu Menu} entity.
 */
public class MenuDTO implements Serializable {
    private int id;
    private MealDTO meal;
    private int numMenu;
    private Set<DishDTO> dishes;

    public MenuDTO(int id, MealDTO meal, int numMenu, Set<DishDTO> dishes) {
        this.id = id;
        this.meal = meal;
        this.numMenu = numMenu;
        this.dishes = dishes == null ? Collections.EMPTY_SET : dishes;
    }

    public int getId() {
        return id;
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
}