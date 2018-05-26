package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;
import ChildCareTech.model.validators.ValidAdultPhoneNumber;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.Constraint;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a row of the Adult table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Adult table in the database.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
@ValidAdultPhoneNumber(message = "Numero di telefono non valido")
public class Adult implements iEntity<Adult, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(nullable = false, unique = true)
    private Person person;

    @ManyToMany(mappedBy = "contacts")
    private Set<Kid> contacts;

    @Column(insertable = false, updatable = false)
    private int role;

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Adult() {
    }

    /**
     * Create an Adult entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param person the person associated to this entity.
     */
    public Adult(Person person) {
        this.person = person;
    }

    /**
     * Create an Adult entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param person the person associated to this entity.
     */
    public Adult(int id, Person person){
        this.id = id;
        this.person = person;
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
     * @return the entity's associated {@link Person}.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @return a Set containing all the {@link Kid Kid(s)} of which this adult is a contact.
     */
    public Set<Kid> getContacts() {
        return contacts == null ? Collections.EMPTY_SET : contacts;
    }

    /**
     * @param contacts a Set containing the {@link Kid Kid(s)} of which this adult is a contact.
     */
    public void setContacts(Set<Kid> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adult)) return false;
        return this.person.equals(((Adult) o).person);
    }

    @Override
    public int hashCode() {
        return person.hashCode();
    }

    public int getRole() { return role; }

    private void setId(int id) {
        this.id = id;
    }

    private void setPerson(Person person) {
        this.person = person;
    }

    private void setRole(int role) { this.role = role; }
}
