package ChildCareTech;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class Supplier extends Adult {
    public Supplier() { super(); }
    public Supplier(Person person) { super(person); }
}
