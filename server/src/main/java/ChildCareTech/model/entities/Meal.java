package ChildCareTech.model.entities;

import ChildCareTech.common.EventStatus;
import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Represents a row of the Meal table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Food Meal in the database.
 */
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

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Meal() {}

    /**
     * Create a Meal entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param canteen the canteen where this meal is hosted.
     * @param mealNum the daily number of this meal.
     * @param workDay the day this meal occurs.
     * @param entryEvent the meal entrance event.
     * @param exitEvent the meal exit event.
     * @param status the meal status.
     * @param menu the meal's menu.
     */
    public Meal(Canteen canteen, int mealNum, WorkDay workDay, Event entryEvent, Event exitEvent, EventStatus status, Menu menu) {
        this.canteen = canteen;
        this.mealNum = mealNum;
        this.workDay = workDay;
        this.entryEvent = entryEvent;
        this.exitEvent = exitEvent;
        this.status = status;
        this.menu = menu;
    }

    /**
     * Create a Meal entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param canteen the canteen where this meal is hosted.
     * @param mealNum the daily number of this meal.
     * @param workDay the day this meal occurs.
     * @param entryEvent the meal entrance event.
     * @param exitEvent the meal exit event.
     * @param status the meal status.
     * @param menu the meal's menu.
     */
    public Meal(int id, Canteen canteen, int mealNum, WorkDay workDay, Event entryEvent, Event exitEvent, EventStatus status, Menu menu) {
        this.id = id;
        this.canteen = canteen;
        this.mealNum = mealNum;
        this.workDay = workDay;
        this.entryEvent = entryEvent;
        this.exitEvent = exitEvent;
        this.status = status;
        this.menu = menu;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    /**
     * @return the entity's id.
     */
    public int getId() {
        return id;
    }

    /**
     * @return the canteen where this meal is hosted.
     */
    public Canteen getCanteen() {
        return canteen;
    }

    /**
     * @return the daily number of this meal.
     */
    public int getMealNum() {
        return mealNum;
    }

    /**
     * @return the day this meal occurs.
     */
    public WorkDay getWorkDay() {
        return workDay;
    }

    /**
     * @return the meal's entrance event.
     */
    public Event getEntryEvent() {
        return entryEvent;
    }

    /**
     * @return the meal's exit event.
     */
    public Event getExitEvent() {
        return exitEvent;
    }

    /**
     * @return the meal's status.
     */
    public EventStatus getStatus() {
        return status;
    }

    /**
     * @param status the meal's new status.
     */
    public void setStatus(EventStatus status) {
        this.status = status;
    }

    /**
     * @return the meal's menu.
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @param menu the meal's new menu.
     */
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

    private void setId(int id) {
        this.id = id;
    }

    private void setCanteen(Canteen canteen) {
        this.canteen = canteen;
    }

    private void setMealNum(int mealNum) {
        this.mealNum = mealNum;
    }

    private void setWorkDay(WorkDay workDay) {
        this.workDay = workDay;
    }

    private void setEntryEvent(Event entryEvent) {
        this.entryEvent = entryEvent;
    }

    private void setExitEvent(Event exitEvent) {
        this.exitEvent = exitEvent;
    }
}
