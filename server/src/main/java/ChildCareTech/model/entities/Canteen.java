package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a row of the Canteen table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Canteen table in the database.
 */
@Entity
public class Canteen implements iEntity<Canteen, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "canteen")
    private Set<Meal> meals;

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Canteen() {
    }

    /**
     * Create a Canteen entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param name the canteen's name
     */
    public Canteen(String name) {
        this.name = name;
    }

    /**
     * Create a Canteen entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param name the canteen's name
     * @param meals a Set cointaining all the canteen's associated {@link Meal Meal(s)}
     */
    public Canteen(String name, Set<Meal> meals) {
        this.name = name;
        this.meals = meals;
    }

    /**
     * Create a Canteen entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param name the canteen's name
     * @param meals a Set cointaining all the canteen's associated {@link Meal Meal(s)}
     */
    public Canteen(int id, String name, Set<Meal> meals) {
        this.id = id;
        this.name = name;
        this.meals = meals;
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
     * @return the canteen's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return a Set containing all the {@link Meal Meals(s)} associated to this Canteen.
     */
    public Set<Meal> getMeals() {
        return meals == null ? Collections.EMPTY_SET : meals;
    }

    /**
     * @param meals a Set containing the {@link Meal Meals(s)} associated to this Canteen.
     */
    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Canteen)) return false;
        return this.name.equals(((Canteen) o).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }
}
