package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.time.LocalDate;

@javax.persistence.Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"canteen_id", "mealNum", "workDay_id"}))
public class Meal implements iEntity<Meal, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Canteen canteen;

    @ManyToOne
    @JoinColumn(nullable = false)
    private WorkDay workDay;

    @Column(nullable = false)
    private int mealNum;

    @OneToOne
    private Event entryEvent;
    @OneToOne
    private Event exitEvent;

    public Meal() {
    }

    public Meal(Canteen canteen, int mealNum, WorkDay workDay, Event entryEvent, Event exitEvent) {
        this.canteen = canteen;
        this.mealNum = mealNum;
        this.workDay = workDay;
        this.entryEvent = entryEvent;
        this.exitEvent = exitEvent;
    }

    public Meal(int id, Canteen canteen, int mealNum, WorkDay workDay, Event entryEvent, Event exitEvent) {
        this.id = id;
        this.canteen = canteen;
        this.mealNum = mealNum;
        this.workDay = workDay;
        this.entryEvent = entryEvent;
        this.exitEvent = exitEvent;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    public LocalDate getDate() {
        return workDay.getDate();
    }

    private void setId(int id) {
        this.id = id;
    }

    public Canteen getCanteen() {
        return canteen;
    }

    private void setCanteen(Canteen canteen) {
        this.canteen = canteen;
    }

    public int getMealNum() {
        return mealNum;
    }

    private void setMealNum(int mealNum) {
        this.mealNum = mealNum;
    }

    public int getId() {
        return id;
    }

    private void setWorkDay(WorkDay workDay) {
        this.workDay = workDay;
    }

    public WorkDay getWorkDay() {
        return workDay;
    }

    public Event getEntryEvent() {
        return entryEvent;
    }

    private void setEntryEvent(Event entryEvent) {
        this.entryEvent = entryEvent;
    }

    public Event getExitEvent() {
        return exitEvent;
    }

    private void setExitEvent(Event exitEvent) {
        this.exitEvent = exitEvent;
    }

    @Override
    public int hashCode() {
        return (Integer.toString(mealNum) + canteen.hashCode() + workDay.hashCode()).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;
        return this.canteen.equals(((Meal) o).canteen) &&
                this.workDay.equals(((Meal) o).workDay) &&
                mealNum == ((Meal) o).mealNum;
    }
}
