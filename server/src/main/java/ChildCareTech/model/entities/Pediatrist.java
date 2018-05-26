package ChildCareTech.model.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a row of the Adult table, with role=1, saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Adult table in the database.
 */
@Entity
@DiscriminatorValue("1")
public class Pediatrist extends Adult {

    @OneToMany(mappedBy = "pediatrist")
    private Set<Kid> kids = new HashSet<>();

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Pediatrist() {
        super();
    }

    /**
     * Create a Pediatrist entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param person the person associated to this entity.
     */
    public Pediatrist(Person person) {
        super(person);
    }

    /**
     * Create a Pediatrist entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param person the person associated to this entity.
     */
    public Pediatrist(int id, Person person) {
        super(id, person);
    }

    /**
     * @return a Set containing all the {@link Kid Kid(s)} assisted by this pediatrist.
     */
    public Set<Kid> getKids() {
        return kids == null ? Collections.EMPTY_SET : kids;
    }

    /**
     * @param kids a Set containing the {@link Kid Kid(s)} assisted by this pediatrist.
     */
    public void setKids(Set<Kid> kids) {
        this.kids = kids;
    }
}
