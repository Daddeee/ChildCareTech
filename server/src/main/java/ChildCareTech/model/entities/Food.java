package ChildCareTech.model.entities;


import ChildCareTech.model.iEntity;
import jdk.nashorn.internal.objects.annotations.Property;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a row of the Food table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Food table in the database.
 */
@javax.persistence.Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Food implements iEntity<Food, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @ColumnDefault("0")
    private boolean isDrink;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "food")
    private Set<Supply> supplies;

    @ManyToMany(mappedBy = "allergies")
    private Set<Person> allergies;

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Food() {
    }

    /**
     * Create a Food entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param name the food's name.
     * @param isDrink is it a drink?
     */
    public Food(String name, boolean isDrink) {
        this.name = name;
        this.isDrink = isDrink;
    }

    /**
     * Create a Food entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param name the food's name.
     * @param isDrink is it a drink?
     */
    public Food(int id, String name, boolean isDrink) {
        this.id = id;
        this.name = name;
        this.isDrink = isDrink;
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
     * @return the food's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return true if the food is a drink, false otherwise.
     */
    public boolean isDrink() {
        return isDrink;
    }

    /**
     * @return a Set containing all the {@link Person People} allergic to this food.
     */
    public Set<Person> getAllergies() {
        return allergies;
    }

    /**
     * @param allergies a Set containing the {@link Person People} allergic to this food.
     */
    public void setAllergies(Set<Person> allergies) {
        this.allergies = allergies;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        return this.name.equals(((Food) o).name);
    }
    public Set<Supply> getSupplies() {
        return supplies == null ? Collections.EMPTY_SET : supplies;
    }

    public void setSupplies(Set<Supply> supplies) {
        this.supplies = supplies;
    }

    private void setId(int id) { this.id = id; }

    private void setName(String name) {
        this.name = name;
    }

    private void setIsDrink(boolean isDrink) {
        this.isDrink = isDrink;
    }
}
