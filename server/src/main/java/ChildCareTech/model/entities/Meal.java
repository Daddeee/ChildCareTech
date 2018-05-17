package ChildCareTech.model.entities;

import ChildCareTech.common.EventStatus;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Event entryEvent;
    @OneToOne(cascade = CascadeType.ALL)
    private Event exitEvent;

    private EventStatus status;

    @OneToOne
    private Menu menu;

    public Meal() {
    }

    public Meal(Canteen canteen, int mealNum, WorkDay workDay, Event entryEvent, Event exitEvent, EventStatus status, Menu menu) {
        this.canteen = canteen;
        this.mealNum = mealNum;
        this.workDay = workDay;
        this.entryEvent = entryEvent;
        this.exitEvent = exitEvent;
        this.status = status;
    }

    public Meal(int id, Canteen canteen, int mealNum, WorkDay workDay, Event entryEvent, Event exitEvent, EventStatus status, Menu menu) {
        this.id = id;
        this.canteen = canteen;
        this.mealNum = mealNum;
        this.workDay = workDay;
        this.entryEvent = entryEvent;
        this.exitEvent = exitEvent;
        this.status = status;
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

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public int hashCode() {
        if(canteen == null || workDay == null) return Integer.toString(mealNum).hashCode();
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
