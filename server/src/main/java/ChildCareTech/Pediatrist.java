package ChildCareTech;

import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public class Pediatrist extends Adult {
    public Pediatrist() { super(); }
    public Pediatrist(Person person) { super(person); }
}
