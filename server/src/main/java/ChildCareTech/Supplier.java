package ChildCareTech;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class Supplier extends Person{
}
