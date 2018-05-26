package ChildCareTech.model.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Represents a row of the Adult table, with role=3, saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Adult table in the database.
 */
@Entity
@DiscriminatorValue("3")
public class Staff extends Adult {

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Staff() {
        super();
    }

    /**
     * Create a Staff entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param person the person associated to this entity.
     */
    public Staff(Person person) {
        super(person);
    }

    /**
     * Create a Staff entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param person the person associated to this entity.
     */
    public Staff(int id, Person person) {
        super(id, person);
    }
}
