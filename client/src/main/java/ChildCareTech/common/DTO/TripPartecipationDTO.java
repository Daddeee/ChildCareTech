package ChildCareTech.common.DTO;

import java.io.Serializable;

/**
 * This class provides a Data Transfer Object encapsulation for TripPartecipation entity.
 */
public class TripPartecipationDTO implements Serializable {
    private int id;
    private PersonDTO person;
    private TripDTO trip;
    private BusDTO bus;

    public TripPartecipationDTO(int id, PersonDTO person, TripDTO trip, BusDTO bus) {
        this.id = id;
        this.person = person;
        this.trip = trip;
        this.bus = bus;
    }

    public int getId() {
        return id;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public BusDTO getBus() {
        return bus;
    }

    public void setBus(BusDTO bus) {
        this.bus = bus;
    }
}