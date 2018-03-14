package ChildCareTech.model;

import javax.persistence.*;
import java.time.LocalDate;

@javax.persistence.Entity
@Table(name = "meals",
        uniqueConstraints = @UniqueConstraint(columnNames = {"canteen_id", "mealNum", "workDay_id"})
)
public class Meal implements iEntity<Meal, Integer> {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Canteen canteen;

    @ManyToOne
    @JoinColumn(nullable = false)
    private WorkDay workDay;

    private int mealNum;


    public Meal() {}

    public Meal(Canteen canteen, int mealNum, WorkDay workDay) {
        this.canteen = canteen;
        this.mealNum = mealNum;
        this.workDay = workDay;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }
    @Override
    public void setPrimaryKey(Meal a) { this.id = a.getPrimaryKey(); }

    public LocalDate getDate() { return workDay.getDate(); }

    private void setId(int id) { this.id = id; }

    public Canteen getCanteen() { return canteen; }

    private void setCanteen_id(Canteen canteen_id) { this.canteen = canteen; }

    public int getMealNum() { return mealNum; }

    private void setMealNum(int meal_num) { this.mealNum = mealNum; }

    public int getId() { return id; }

    private void setWorkDay(WorkDay workDay) { this.workDay = workDay; }

    @Override
    public int hashCode() {
        return (Integer.toString(mealNum) + canteen.hashCode() + workDay.hashCode()).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Meal)) return false;
        return this.canteen.equals(((Meal) o).canteen) &&
                this.workDay.equals(((Meal) o).workDay) &&
                mealNum == ((Meal) o).mealNum;
    }
}
