package ChildCareTech;

import javax.persistence.*;

@Entity
@DiscriminatorValue("3")
public class Staff extends Person {
}
