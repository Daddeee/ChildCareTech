package ChildCareTech.model;

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
        this.time = time;
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
        this.time = time;
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
}
