package ChildCareTech.model.entities;

import ChildCareTech.common.EventStatus;
import ChildCareTech.common.EventType;
import ChildCareTech.model.iEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a row of the Event table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Event table in the database.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "workDay_id"}))
public class Event implements iEntity<Event, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(length = 40)
    private String name;

    @ManyToOne
    private WorkDay workDay;

    private LocalTime beginTime;

    private LocalTime endTime;

    private EventType eventType;

    private EventStatus eventStatus;

    @OneToMany(mappedBy = "event")
    private Set<Checkpoint> checkpoints;

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Event(){}

    /**
     * Create an Event entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param name the event's name.
     * @param workDay the day the event occurs.
     * @param beginTime the beginning time of the event.
     * @param endTime the ending time of the event.
     * @param eventType the {@link EventType type} of the event.
     * @param eventStatus the {@link EventStatus status} of the event.
     */
    public Event(String name, WorkDay workDay, LocalTime beginTime, LocalTime endTime, EventType eventType, EventStatus eventStatus){
        this.name = name;
        this.workDay = workDay;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.eventType = eventType;
        this.eventStatus = eventStatus;
    }

    /**
     * Create an Event entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param name the event's name.
     * @param workDay the day the event occurs.
     * @param beginTime the beginning time of the event.
     * @param endTime the ending time of the event.
     * @param eventType the {@link EventType type} of the event.
     * @param eventStatus the {@link EventStatus status} of the event.
     */
    public Event(int id, String name, WorkDay workDay, LocalTime beginTime, LocalTime endTime, EventType eventType, EventStatus eventStatus){
        this.id = id;
        this.name = name;
        this.workDay = workDay;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.eventType = eventType;
        this.eventStatus = eventStatus;
    }

    @Override
    public Integer getPrimaryKey() {
        return getId();
    }

    /**
     * @return the id of the row in the database.
     */
    public int getId() {
        return id;
    }

    /**
     * @return the event's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the day the event occurs.
     */
    public WorkDay getWorkDay() {
        return workDay;
    }

    /**
     * @return the beginning time of the event.
     */
    public LocalTime getBeginTime() {
        return beginTime;
    }

    /**
     * @return the ending time of the event.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @return the {@link EventType type} of the event.
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * @return the {@link EventStatus status} of the event.
     */
    public EventStatus getEventStatus() {
        return eventStatus;
    }

    /**
     * @param eventStatus the new {@link EventStatus status} of the event.
     */
    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    /**
     * @return a Set containing all the {@link Checkpoint Checkpoint(s)} associated to this event.
     */
    public Set<Checkpoint> getCheckpoints() {
        return checkpoints == null ? Collections.emptySet() : checkpoints;
    }

    /**
     * @param checkpoints a Set containing the {@link Checkpoint Checkpoint(s)} associated to this event.
     */
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
