package ChildCareTech.model.entities;


import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "workdays")
public class WorkDay implements iEntity<WorkDay, Integer> {
    public static final int EVENT_TIME_DURATION = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private LocalDate date;

    private LocalTime entryTime;

    private LocalTime exitTime;

    private boolean isHoliday;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workDay")
    private Set<Meal> meals;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workDay")
    private Set<Checkpoint> checkpoints;

    public WorkDay() {
    }

    public WorkDay(LocalDate date, LocalTime entryTime, LocalTime exitTime, boolean isHoliday) {
        this.date = date;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.isHoliday = isHoliday;
    }

    public WorkDay(int id, LocalDate date, LocalTime entryTime, LocalTime exitTime, boolean isHoliday) {
        this.id = id;
        this.date = date;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.isHoliday = isHoliday;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Set<Meal> getMeals() {
        return meals == null ? Collections.EMPTY_SET : meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    public Set<Checkpoint> getCheckpoints() {
        return checkpoints == null ? Collections.EMPTY_SET : checkpoints;
    }

    public void setCheckpoints(Set<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public List<EventDTO> getEvents(){
        List<EventDTO> events = new ArrayList<>();

        events.add(new EventDTO(
                "Ingresso",
                getEntryTime(),
                getEntryTime().plusMinutes(EVENT_TIME_DURATION)
        ));

        events.add(new EventDTO(
                "Uscita",
                getExitTime(),
                getExitTime().plusMinutes(EVENT_TIME_DURATION)
        ));

        return events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkDay)) return false;
        return this.date.equals(((WorkDay) o).date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
