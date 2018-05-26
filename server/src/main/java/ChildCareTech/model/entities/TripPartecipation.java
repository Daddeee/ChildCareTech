package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;

/**
 * Represents a row of the TripPartecipation table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the TripPartecipation table in the database.
 */
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

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public TripPartecipation() {}

    /**
     * Create a TripPartecipation entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param person the person partecipating to the trip.
     * @param trip the trip of which I save the participation.
     * @param bus bus on which the person must travel.
     */
    public TripPartecipation(Person person, Trip trip, Bus bus) {
        this.person = person;
        this.trip = trip;
        this.bus = bus;
    }

    /**
     * Create a TripPartecipation entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param person the person partecipating to the trip.
     * @param trip the trip of which I save the participation.
     * @param bus bus on which the person must travel.
     */
    public TripPartecipation(int id, Person person, Trip trip, Bus bus) {
        this.id = id;
        this.person = person;
        this.trip = trip;
        this.bus = bus;
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
     * @return the person partecipating to the trip.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @return the trip of this participation.
     */
    public Trip getTrip() {
        return trip;
    }

    /**
     * @return the bus on which the person must travel.
     */
    public Bus getBus() {
        return bus;
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

    private void setId(int id) {
        this.id = id;
    }

    private void setTrip(Trip trip) {
        this.trip = trip;
    }

    private void setPerson(Person person) {
        this.person = person;
    }

    private void setBus(Bus bus) {
        this.bus = bus;
    }
}
