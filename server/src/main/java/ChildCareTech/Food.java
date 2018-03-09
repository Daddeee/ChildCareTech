package ChildCareTech;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "foods")
public class Food {

    @Id
    private int id;

    public Food() { }
}
