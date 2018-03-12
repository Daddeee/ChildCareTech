package ChildCareTech.entities;


import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "workdays")
public class WorkDay implements iEntity<WorkDay, Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private LocalDate date;

    @OneToMany(mappedBy = "workDay")
    private Set<Meal> meals;

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
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }
}
