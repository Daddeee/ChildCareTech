package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"person_id", "trip_id"}))
public class TripPartecipation implements iEntity<TripPartecipation, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Trip trip;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    private Bus bus;

    public TripPartecipation() {
    }

    public TripPartecipation(Person person, Trip trip, Bus bus) {
        this.person = person;
        this.trip = trip;
        this.bus = bus;
    }

    public TripPartecipation(int id, Person person, Trip trip, Bus bus) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripPartecipation)) return false;
        return this.person.equals(((TripPartecipation) o).person) &&
                this.trip.equals(((TripPartecipation) o).trip);
    }

    @Override
    public int hashCode() {
        if(person == null || trip == null) return super.hashCode();
        return (Integer.toString(person.hashCode()) + trip.hashCode()).hashCode();
    }
}
