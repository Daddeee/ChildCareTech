package ChildCareTech.model;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@DiscriminatorValue("3")
public class Staff extends Adult {
    public Staff() { super(); }
    public Staff(Person person) { super(person); }
}
