package ChildCareTech;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "adults")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("not null")
public class Adult implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "fiscal_code", unique = true)
    private Person person;

    @Column(name = "role", nullable = false)
    private int role;

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

    public int getRole() {
        return role;
    }

    private void setRole(int role) {
        this.role = role;
    }
}
