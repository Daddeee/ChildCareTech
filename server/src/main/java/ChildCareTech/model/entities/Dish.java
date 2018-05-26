package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a row of the Dish table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Dish table in the database.
 */
@javax.persistence.Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Dish implements iEntity<Dish, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "dishes")
    private Set<Menu> menus;

    @ManyToMany(targetEntity = Food.class)
    private Set<Food> foods;

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Dish() {}

    /**
     * Create a Dish entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param name the name of this dish.
     */
    public Dish(String name) {
        this.name = name;
    }

    /**
     * Create a Dish entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param name the name of this dish.
     */
    public Dish(int id, String name) {
        this.id = id;
        this.name = name;
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
     * @return the name of this dish.
     */
    public String getName() {
        return name;
    }

    /**
     * @return a Set containing all the {@link Menu Menu(s)} associated to this dish.
     */
    public Set<Menu> getMenus() {
        return menus;
    }

    /**
     * @param menus a Set containing the {@link Menu Menu(s)} associated to this dish.
     */
    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    /**
     * @return a Set containing all the {@link Food Food(s)} associated to this dish.
     */
    public Set<Food> getFoods() {
        return foods == null ? Collections.EMPTY_SET : foods;
    }

    /**
     * @param foods a Set containing the {@link Food Food(s)} associated to this dish.
     */
    public void setFoods(Set<Food> foods) {
        this.foods = foods;
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

    private void setName(String name) {
        this.name = name;
    }

}
