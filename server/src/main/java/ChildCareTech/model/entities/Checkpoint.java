package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * Represents a row of the Checkpoint table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Checkpoint table in the database.
 */
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

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Checkpoint() {}

    /**
     * Create a Checkpoint entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param event the {@link Event} for which this Checkpoint is created.
     * @param person the {@link Person} for whom this Checkpoint is created.
     * @param time the time of registration.
     */
    public Checkpoint(Event event, Person person, LocalTime time) {
        this.event = event;
        this.person = person;
        this.time = time.minusNanos(time.getNano());
    }

    /**
     * Create a Checkpoint entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param event the {@link Event} for which this Checkpoint is created.
     * @param person the {@link Person} for whom this Checkpoint is created.
     * @param time the time of registration.
     */
    public Checkpoint(int id, Event event, Person person, LocalTime time) {
        this.id = id;
        this.event = event;
        this.person = person;
        this.time = time.minusNanos(time.getNano());
    }

    @Override
    public Integer getPrimaryKey() {
        return getId();
    }

    /**
     * @return the entity's id.
     */
    public int getId() {
        return id;
    }

    /**
     * @return the checkpoint's {@link Person}.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @return the checkpoint's {@link Event}.
     */
    public Event getEvent() {
        return event;
    }

    /**
     * @return the checkpoint's registration time.
     */
    public LocalTime getTime() {
        return time;
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

    private void setId(int id) {
        this.id = id;
    }

    private void setPerson(Person person) {
        this.person = person;
    }

    private void setEvent(Event day) {
        this.event = day;
    }

    private void setTime(LocalTime time) {
        this.time = time.minusNanos(time.getNano());
    }
}
