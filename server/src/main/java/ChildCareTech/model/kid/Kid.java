package ChildCareTech.model.kid;

import ChildCareTech.model.adult.Adult;
import ChildCareTech.model.iEntity;
import ChildCareTech.model.pediatrist.Pediatrist;
import ChildCareTech.model.person.Person;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "kids")
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
    @JoinTable(name = "contacts")
    private Set<Adult> contacts;

    public Kid() {
    }

    public Kid(Person person, Adult firstTutor, Adult secondTutor, Pediatrist pediatrist) {
        this.person = person;
        this.firstTutor = firstTutor;
        this.secondTutor = secondTutor;
        this.pediatrist = pediatrist;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    @Override
    public void setPrimaryKey(Kid a) {
        this.id = a.getPrimaryKey();
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    private void setPerson(Person person) {
        this.person = person;
    }

    public Adult getFirstTutor() {
        return firstTutor;
    }

    private void setFirstTutor(Adult firstTutor) {
        this.firstTutor = firstTutor;
    }

    public Adult getSecondTutor() {
        return secondTutor;
    }

    private void setSecondTutor(Adult secondTutor) {
        this.secondTutor = secondTutor;
    }

    public Pediatrist getPediatrist() {
        return pediatrist;
    }

    private void setPediatrist(Pediatrist pediatrist) {
        this.pediatrist = pediatrist;
    }

    public Set<Adult> getContacts() {
        return contacts == null ? Collections.EMPTY_SET : contacts;
    }

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
}

