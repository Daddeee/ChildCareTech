package ChildCareTech;

import javax.persistence.*;

@Entity
@DiscriminatorValue("3")
public class Staff extends Adult {
    public Staff() { super(); }
    public Staff(Person person) { super(person); }
}
