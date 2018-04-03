package ChildCareTech.model.adult;

import ChildCareTech.model.kid.Kid;
import ChildCareTech.model.person.Person;
import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "adults")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
public class Adult implements iEntity<Adult, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(nullable = false, unique = true)
    private Person person;

    @ManyToMany
    @JoinTable(name="contacts")
    private Set<Kid> contacts;

    public Adult() {
    }

    public Adult(Person person) {
        this.person = person;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    @Override
    public void setPrimaryKey(Adult a) { this.id = a.getPrimaryKey(); }

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

    public Set<Kid> getContacts() { return contacts == null ? Collections.EMPTY_SET : contacts; }

    public void setContacts(Set<Kid> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Adult)) return false;
        return this.person.equals(((Adult) o).person);
    }

    @Override
    public int hashCode() {
        return person.hashCode();
    }
}
