package ChildCareTech;


import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "foods",
        uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Food implements iEntity<Integer> {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    public Food() { }

    public Food(String name) { this.name = name; }

    private void setName(String name) { this.name = name; }

    public String getName() { return name; }

    @Override
    public Integer getPrimaryKey() { return id; }

}
