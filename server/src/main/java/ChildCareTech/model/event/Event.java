package ChildCareTech.model.event;

import ChildCareTech.model.person.Person;
import ChildCareTech.model.workday.WorkDay;
import ChildCareTech.model.iEntity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name="events",
        uniqueConstraints = @UniqueConstraint(columnNames = {"workDay_id", "person_fiscalCode", "time"})
)
public class Event implements iEntity<Event, Integer> {
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

    public Event(){}
    public Event(WorkDay workDay, Person person, LocalTime time, boolean isIn){
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
    public void setPrimaryKey(Event o) {
        setId(o.getPrimaryKey());
    }

    @Override
    public int hashCode() {
        return (Integer.toString(person.hashCode()) + workDay.hashCode() + time).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Event)) return false;

        Event e = (Event) o;
        return this.workDay.equals(e.workDay) &&
                this.time.equals(e.time) &&
                this.person.equals(e.person);
    }
}
