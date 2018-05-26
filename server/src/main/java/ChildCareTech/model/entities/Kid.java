package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;
import ChildCareTech.model.validators.ValidTutors;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a row of the Kid table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Kid table in the database.
 */
@javax.persistence.Entity
@ValidTutors(message = "Specificare almeno un tutore.")
public class Kid implements iEntity<Kid, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(unique = true, nullable = false)
    private Person person;

    @OneToOne
    private Adult firstTutor;

    @OneToOne
    private Adult secondTutor;

    @ManyToOne
    private Pediatrist pediatrist;

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "kid_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "adult_id", nullable = false, updatable = false)})
    private Set<Adult> contacts;

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Kid() {}

    /**
     * Create a Kid entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param person the {@link Person} associated to this entity.
     * @param firstTutor the kid's first tutor.
     * @param secondTutor the kid's second tutor.
     * @param pediatrist the kid's pediatrist
     */
    public Kid(Person person, Adult firstTutor, Adult secondTutor, Pediatrist pediatrist) {
        this.person = person;
        this.firstTutor = firstTutor;
        this.secondTutor = secondTutor;
        this.pediatrist = pediatrist;
    }

    /**
     * Create a Kid entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param person the {@link Person} associated to this entity.
     * @param firstTutor the kid's first tutor.
     * @param secondTutor the kid's second tutor.
     * @param pediatrist the kid's pediatrist
     */
    public Kid(int id, Person person, Adult firstTutor, Adult secondTutor, Pediatrist pediatrist) {
        this.id = id;
        this.person = person;
        this.firstTutor = firstTutor;
        this.secondTutor = secondTutor;
        this.pediatrist = pediatrist;
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
     * @return the associated person.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @return the kid's first tutor.
     */
    public Adult getFirstTutor() {
        return firstTutor;
    }

    /**
     * @return the kid's second tutor.
     */
    public Adult getSecondTutor() {
        return secondTutor;
    }

    /**
     * @return the kid's pediatrist.
     */
    public Pediatrist getPediatrist() {
        return pediatrist;
    }

    /**
     * @return a Set containing all the {@link Adult Adult(s)} that are a contact for this .
     */
    public Set<Adult> getContacts() {
        return contacts == null ? Collections.EMPTY_SET : contacts;
    }

    /**
     * @param contacts a Set containing the {@link Adult Adult(s)} that are a contact for this .
     */
    public void setContacts(Set<Adult> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kid)) return false;
        return this.person.equals(((Kid) o).person);
    }

    @Override
    public int hashCode() {
        return person.hashCode();
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setPerson(Person person) {
        this.person = person;
    }

    private void setFirstTutor(Adult firstTutor) {
        this.firstTutor = firstTutor;
    }

    private void setSecondTutor(Adult secondTutor) {
        this.secondTutor = secondTutor;
    }

    private void setPediatrist(Pediatrist pediatrist) {
        this.pediatrist = pediatrist;
    }
}

