package ChildCareTech.common.DTO;

import java.io.Serializable;

public class TripPartecipationDTO implements Serializable {
    private PersonDTO person;
    private TripDTO trip;
    private BusDTO bus;

    public TripPartecipationDTO(PersonDTO person, TripDTO trip, BusDTO bus) {
        this.person = person;
        this.trip = trip;
        this.bus = bus;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public BusDTO getBus() {
        return bus;
    }
}