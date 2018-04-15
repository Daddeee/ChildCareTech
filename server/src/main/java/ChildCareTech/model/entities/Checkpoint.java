package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "events",
        uniqueConstraints = @UniqueConstraint(columnNames = {"workDay_id", "person_fiscalCode", "time"})
)
public class Checkpoint implements iEntity<Checkpoint, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private WorkDay workDay;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Person person;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private boolean isIn;

    public Checkpoint() {
    }

    public Checkpoint(WorkDay workDay, Person person, LocalTime time, boolean isIn) {
        this.workDay = workDay;
        this.person = person;
        this.time = time.minusNanos(time.getNano());
        this.isIn = isIn;
    }

    public Checkpoint(int id, WorkDay workDay, Person person, LocalTime time, boolean isIn) {
        this.id = id;
        this.workDay = workDay;
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

    public WorkDay getWorkDay() {
        return workDay;
    }

    private void setWorkDay(WorkDay day) {
        this.workDay = day;
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
        return (Integer.toString(person.hashCode()) + workDay.hashCode() + time).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Checkpoint)) return false;

        Checkpoint e = (Checkpoint) o;
        return this.workDay.equals(e.workDay) &&
                this.time.equals(e.time) &&
                this.person.equals(e.person);
    }
}
