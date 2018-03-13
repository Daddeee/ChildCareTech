package ChildCareTech.model;

import javax.persistence.*;

@Entity
public class TripPartecipation implements iEntity<TripPartecipation, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Bus bus;

    public TripPartecipation(){}
    public TripPartecipation(Person person, Trip trip, Bus bus){
        this.person = person;
        this.trip = trip;
        this.bus = bus;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    private void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Person getPerson() {
        return person;
    }

    private void setPerson(Person person) {
        this.person = person;
    }

    public Bus getBus() {
        return bus;
    }

    private void setBus(Bus bus) {
        this.bus = bus;
    }

    @Override
    public Integer getPrimaryKey() {
        return getId();
    }

    @Override
    public void setPrimaryKey(TripPartecipation o) {
        setId(o.getId());
    }
}
