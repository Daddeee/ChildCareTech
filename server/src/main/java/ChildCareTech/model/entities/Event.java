package ChildCareTech.model.entities;

import ChildCareTech.common.EventStatus;
import ChildCareTech.common.EventType;
import ChildCareTech.model.iEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "workDay_id"}))
public class Event implements iEntity<Event, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    @ManyToOne
    private WorkDay workDay;

    private LocalTime beginTime;

    private LocalTime endTime;

    private EventType eventType;

    private EventStatus eventStatus;

    @OneToMany(mappedBy = "event")
    private Set<Checkpoint> checkpoints;

    public Event(){}

    public Event(int id, String name, WorkDay workDay, LocalTime beginTime, LocalTime endTime, EventType eventType, EventStatus eventStatus){
        this.id = id;
        this.name = name;
        this.workDay = workDay;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.eventType = eventType;
        this.eventStatus = eventStatus;
    }

    public Event(String name, WorkDay workDay, LocalTime beginTime, LocalTime endTime, EventType eventType, EventStatus eventStatus){
        this.name = name;
        this.workDay = workDay;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.eventType = eventType;
        this.eventStatus = eventStatus;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WorkDay getWorkDay() {
        return workDay;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public Integer getPrimaryKey() {
        return getId();
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Set<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(Set<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        if (this.id != 0 && ((Event) o).id != 0) return this.id == ((Event) o).id;
        return this.name.equals(((Event) o).name) &&
                this.workDay.equals(((Event) o).workDay);
    }

    @Override
    public String toString() {
        String beginTime = getBeginTime() == null ? "" : getBeginTime().toString();
        String endTime = getEndTime() == null ? "" : getEndTime().toString();
        return getName() + " [" + beginTime + " | " + endTime + "] " + getEventStatus().toString();
    }
}
