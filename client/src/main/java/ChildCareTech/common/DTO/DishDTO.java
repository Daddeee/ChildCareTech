package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class DishDTO implements Serializable {
    private int id;
    private String name;
    private MenuDTO menu;
    private Set<FoodDTO> foods;

    public DishDTO(int id, String name, MenuDTO menu, Set<FoodDTO> foods) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.foods = foods == null ? Collections.EMPTY_SET : foods;
    }

    public int getId() {
        return id;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setMenu(MenuDTO menu) {
        this.menu = menu;
    }

    public void setFoods(Set<FoodDTO> foods) {
        this.foods = foods;
    }
}