package ChildCareTech;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "meals",
        uniqueConstraints = @UniqueConstraint(columnNames = {"canteen_id", "mealNum", "date"})
)
public class Meal
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Canteen canteen;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(targetEntity = Menu.class)
    private Menu menu;

    private int mealNum;


    public Meal() {}

    public Meal(Canteen canteen, int mealNum, Date date, Menu menu) {
        this.canteen = canteen;
        this.mealNum = mealNum;
        this.date = date;
        this.menu = menu;
    }

    public Date getDate() { return date; }

    private void setId(int id) { this.id = id; }

    public Canteen getCanteen() { return canteen; }

    private void setCanteen(Canteen canteen) { this.canteen = canteen; }

    public int getMealNum() { return mealNum; }

    private void setMealNum(int mealNum) { this.mealNum = mealNum; }

    public int getId() { return id; }

    private void setDate(Date date) { this.date = date; }

    public Menu getMenu() { return menu; }

    private void setMenu(Menu menu) { this.menu = menu; }
}
