package ChildCareTech;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "meal",
        uniqueConstraints = @UniqueConstraint(columnNames = {"canteen", "meal_num", "date"})
)
public class Meal
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "canteen") //nullable?
    private Canteen canteen;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "meal_num", nullable = false)
    private int meal_num;


    public Meal() {}

    public Meal(Canteen canteen, int meal_num, Date date) {
        this.canteen = canteen;
        this.meal_num = meal_num;
        this.date = date;
    }

    public Date getDate() { return date; }

    private void setId(int id) { this.id = id; }

    public Canteen getCanteen() { return canteen; }

    private void setCanteen_id(Canteen canteen_id) { this.canteen = canteen; }

    public int getMeal_num() { return meal_num; }

    private void setMeal_num(int meal_num) { this.meal_num = meal_num; }

    public int getId() { return id; }

    private void setDate(Date date) { this.date = date; }
}
