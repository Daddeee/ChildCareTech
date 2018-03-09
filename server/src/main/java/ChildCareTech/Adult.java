package ChildCareTech;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "adults")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("not null")
public class Adult implements DAOEntity<Integer>{
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

    public Set<Kid> getContacts() {
        return new HashSet<>(contacts);
    }

    private void setContacts(Set<Kid> contacts) {
        this.contacts = contacts;
    }
}
