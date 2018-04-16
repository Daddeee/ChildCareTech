package ChildCareTech.model.entities;

import ChildCareTech.common.EventStatus;
import ChildCareTech.model.iEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

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

    private EventStatus eventStatus;

    public Event(int id, String name, WorkDay workDay, LocalTime beginTime, LocalTime endTime, EventStatus eventStatus){
        this.id = id;
        this.name = name;
        this.workDay = workDay;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.eventStatus = eventStatus;
    }

    public Event(String name, WorkDay workDay, LocalTime beginTime, LocalTime endTime, EventStatus eventStatus){
        this.name = name;
        this.workDay = workDay;
        this.beginTime = beginTime;
        this.endTime = endTime;
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
}
