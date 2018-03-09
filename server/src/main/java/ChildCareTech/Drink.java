package ChildCareTech;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "drinks")
public class Drink {

    @Id
    private int id;

    private String name;

    @OneToMany
    private Set<Food> foods;

    public Drink() { }

}
