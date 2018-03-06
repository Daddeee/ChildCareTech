package ChildCareTech;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "meal",
        uniqueConstraints = @UniqueConstraint(columnNames = {"canteen_id", "meal_num", "date"})
)
public class Meal
{
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "canteen_id") //nullable?
    private int canteen_id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "meal_num", nullable = false)
    private int meal_num;


    public Meal() {}

    public Meal(int canteen_id, int meal_num, Date date) {
        this.canteen_id = canteen_id;
        this.meal_num = meal_num;
        this.date = date;
    }

    public Date getDate() { return date; }

    private void setId(int id) { this.id = id; }

    public int getCanteen_id() { return canteen_id; }

    private void setCanteen_id(int canteen_id) { this.canteen_id = canteen_id; }

    public int getMeal_num() { return meal_num; }

    private void setMeal_num(int meal_num) { this.meal_num = meal_num; }

    public int getId() { return id; }

    private void setDate(Date date) { this.date = date; }
}
