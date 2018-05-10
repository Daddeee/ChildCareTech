package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class DishDTO implements Serializable {
    private int id;
    private String name;
    private Set<MenuDTO> menus;
    private Set<FoodDTO> foods;

    public DishDTO(int id, String name, Set<MenuDTO> menus, Set<FoodDTO> foods) {
        this.id = id;
        this.name = name;
        this.menus = menus == null ? Collections.EMPTY_SET : menus;
        this.foods = foods == null ? Collections.EMPTY_SET : foods;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<MenuDTO> getMenus() {
        return menus;
    }

    public Set<FoodDTO> getFoods() {
        return foods;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenus(Set<MenuDTO> menus) {
        this.menus = menus;
    }

    public void setFoods(Set<FoodDTO> foods) {
        this.foods = foods;
    }
}