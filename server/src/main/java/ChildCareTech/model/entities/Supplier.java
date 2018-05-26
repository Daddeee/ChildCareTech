package ChildCareTech.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a row of the Adult table, with role=2, saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Adult table in the database.
 */
@Entity
@DiscriminatorValue("2")
public class Supplier extends Adult {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "supplier")
    private Set<Supply> supplies;

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Supplier() {
        super();
    }

    /**
     * Create a Supplier entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param person the person associated to this entity.
     */
    public Supplier(Person person) {
        super(person);
    }

    /**
     * Create a Supplier entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param person the person associated to this entity.
     */
    public Supplier(int id, Person person) {
        super(id, person);
    }

    public Set<Supply> getSupplies() {
        return supplies == null ? Collections.EMPTY_SET : supplies;
    }

    public void setSupplies(Set<Supply> supplies) {
        this.supplies = supplies;
    }

}
