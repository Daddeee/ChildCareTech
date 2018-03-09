package ChildCareTech;


import javax.persistence.*;

@Entity
@Table(name = "foods",
        uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Food {

    @Id
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    public Food() { }

    public Food(String name) { this.name = name; }

    private void setName(String name) { this.name = name; }

    public String getName() { return name; }

}
