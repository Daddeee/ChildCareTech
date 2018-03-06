package ChildCareTech;

import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public class Pediatrist extends Person {
}
