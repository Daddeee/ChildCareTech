package ChildCareTech.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workdays")
public class WorkDay implements iEntity<WorkDay, Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workDay")
    private Set<Meal> meals;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workDay")
    private Set<Event> events;

    public WorkDay() { }

    public WorkDay(LocalDate date) {
        this.date = date;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    @Override
    public void setPrimaryKey(WorkDay workDay) {
        this.id = getPrimaryKey();
    }

    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Set<Meal> getMeals() {
        return new HashSet<>(meals);
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    public Set<Event> getEvents() {
        return new HashSet<>(events);
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof WorkDay)) return false;
        return this.date.equals(((WorkDay) o).date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
