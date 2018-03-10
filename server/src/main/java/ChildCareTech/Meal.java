package ChildCareTech;

import javax.persistence.*;
import java.sql.Date;

@javax.persistence.Entity
@Table(name = "meals",
        uniqueConstraints = @UniqueConstraint(columnNames = {"canteen_id", "mealNum", "date"})
)
public class Meal implements iEntity<Integer> {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Canteen canteen;

    @Column(nullable = false)
    private Date date;

    private int mealNum;


    public Meal() {}

    public Meal(Canteen canteen, int mealNum, Date date) {
        this.canteen = canteen;
        this.mealNum = mealNum;
        this.date = date;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    public Date getDate() { return date; }

    private void setId(int id) { this.id = id; }

    public Canteen getCanteen() { return canteen; }

    private void setCanteen_id(Canteen canteen_id) { this.canteen = canteen; }

    public int getMealNum() { return mealNum; }

    private void setMealNum(int meal_num) { this.mealNum = mealNum; }

    public int getId() { return id; }

    private void setDate(Date date) { this.date = date; }
}
