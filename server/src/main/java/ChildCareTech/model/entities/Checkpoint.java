package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"event_id", "person_id"})
)
public class Checkpoint implements iEntity<Checkpoint, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Person person;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private boolean isIn;

    public Checkpoint() {
    }

    public Checkpoint(Event event, Person person, LocalTime time, boolean isIn) {
        this.event = event;
        this.person = person;
        this.time = time.minusNanos(time.getNano());
        this.isIn = isIn;
    }

    public Checkpoint(int id, Event event, Person person, LocalTime time, boolean isIn) {
        this.id = id;
        this.event = event;
        this.person = person;
        this.time = time.minusNanos(time.getNano());
        this.isIn = isIn;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    private void setPerson(Person person) {
        this.person = person;
    }

    public Event getEvent() {
        return event;
    }

    private void setEvent(Event day) {
        this.event = day;
    }

    public LocalTime getTime() {
        return time;
    }

    private void setTime(LocalTime time) {
        this.time = time.minusNanos(time.getNano());
    }

    public boolean isIn() {
        return isIn;
    }

    private void setIn(boolean in) {
        isIn = in;
    }

    @Override
    public Integer getPrimaryKey() {
        return getId();
    }

    @Override
    public int hashCode() {
        return (Integer.toString(person.hashCode()) + event.hashCode() + time).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Checkpoint)) return false;

        Checkpoint e = (Checkpoint) o;
        return this.event.equals(e.event) &&
                this.time.equals(e.time) &&
                this.person.equals(e.person);
    }
}
