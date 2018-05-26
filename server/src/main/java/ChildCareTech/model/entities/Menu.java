package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a row of the Menu table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Menu table in the database.
 */
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

    @ManyToMany
    private Set<Dish> dishes;

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Menu() {}

    /**
     * Create a Menu entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param meal the menu's meal.
     * @param numMenu the menu's number.
     */
    public Menu(Meal meal, int numMenu) {
        this.meal = meal;
        this.numMenu = numMenu;
    }

    /**
     * Create a Menu entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param meal the menu's meal.
     * @param numMenu the menu's number.
     */
    public Menu(int id, Meal meal, int numMenu) {
        this.id = id;
        this.meal = meal;
        this.numMenu = numMenu;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    /**
     * @return the entity's id.
     */
    public int getId() {
        return id;
    }

    /**
     * @return the menu's number.
     */
    public int getNumMenu() {
        return numMenu;
    }

    /**
     * @return the menu's associated meal.
     */
    public Meal getMeal() {
        return meal;
    }

    /**
     * @param meal the menu's new associated meal.
     */
    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    /**
     * @return a Set containing all the {@link Dish dishes} associated to this menu.
     */
    public Set<Dish> getDishes() {
        return dishes == null ? Collections.EMPTY_SET : dishes;
    }

    /**
     * @param dishes a Set containing the {@link Dish dishes} associated to this menu.
     */
    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
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

    private void setNumMenu(int numMenu) {
        this.numMenu = numMenu;
    }
}