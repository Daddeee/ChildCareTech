package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@javax.persistence.Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Dish implements iEntity<Dish, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    private Set<Menu> menus;

    @ManyToMany(targetEntity = Food.class)
    private Set<Food> foods;

    public Dish() {
    }

    public Dish(String name) {
        this.name = name;
    }

    public Dish(String name, Set<Menu> menus, Set<Food> foods) {
        this.name = name;
        this.menus = menus;
        this.foods = foods;
    }

    public Dish(int id, String name, Set<Menu> menus, Set<Food> foods) {
        this.id = id;
        this.name = name;
        this.menus = menus;
        this.foods = foods;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    public int getId() {
        return id;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Set<Food> getFoods() {
        return foods == null ? Collections.EMPTY_SET : foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;
        return this.name.equals(((Dish) o).name);
    }

}
