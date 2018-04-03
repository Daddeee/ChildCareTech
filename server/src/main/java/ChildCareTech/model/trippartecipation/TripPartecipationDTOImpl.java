package ChildCareTech.model.trippartecipation;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.bus.BusDTOImpl;
import ChildCareTech.model.person.PersonDTOImpl;
import ChildCareTech.model.trip.TripDTOImpl;

public class TripPartecipationDTOImpl implements TripPartecipationDTO {
    private PersonDTO person;
    private TripDTO trip;
    private BusDTO bus;

    public TripPartecipationDTOImpl(TripPartecipation tripPartecipation){
        person = new PersonDTOImpl(tripPartecipation.getPerson());
        trip = new TripDTOImpl(tripPartecipation.getTrip());
        bus = new BusDTOImpl(tripPartecipation.getBus());
    }

    @Override
    public PersonDTO getPerson() {
        return person;
    }

    @Override
    public TripDTO getTrip() {
        return trip;
    }

    @Override
    public BusDTO getBus() {
        return bus;
    }
}