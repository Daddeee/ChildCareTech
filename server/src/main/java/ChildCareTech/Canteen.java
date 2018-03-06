package ChildCareTech;


import javax.persistence.*;

@Entity
@Table(name = "canteen")
public class Canteen {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    public Canteen() {}
    public Canteen(int id) { this.id = id; }

    public int getId() { return id; }

    private void setId(int id) { this.id = id; }

}
