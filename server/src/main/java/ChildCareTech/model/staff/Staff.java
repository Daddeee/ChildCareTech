package ChildCareTech.model.staff;

import ChildCareTech.model.adult.Adult;
import ChildCareTech.model.person.Person;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@DiscriminatorValue("3")
public class Staff extends Adult {
    public Staff() { super(); }
    public Staff(Person person) { super(person); }
}
