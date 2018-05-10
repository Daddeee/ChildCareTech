package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@javax.persistence.Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"meal_id", "numMenu"}))
public class Menu implements iEntity<Menu, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Meal meal;

    @Column(nullable = false)
    private int numMenu;

    @ManyToMany(mappedBy = "menus")
    private Set<Dish> dishes;

    public Menu() {
    }

    public Menu(Meal meal, int numMenu) {
        this.meal = meal;
        this.numMenu = numMenu;
    }

    public Menu(Meal meal, int numMenu, Set<Dish> dishes) {
        this.meal = meal;
        this.numMenu = numMenu;
        this.dishes = dishes;
    }

    public Menu(int id, Meal meal, int numMenu, Set<Dish> dishes) {
        this.id = id;
        this.meal = meal;
        this.numMenu = numMenu;
        this.dishes = dishes;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Set<Dish> getDishes() {
        return dishes == null ? Collections.EMPTY_SET : dishes;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getNumMenu() {
        return numMenu;
    }

    private void setNumMenu(int numMenu) {
        this.numMenu = numMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        return this.meal.equals(((Menu) o).meal) &&
                this.numMenu == ((Menu) o).numMenu;
    }

    @Override
    public int hashCode() {
        return (Integer.toString(numMenu) + meal.hashCode()).hashCode();
    }
}