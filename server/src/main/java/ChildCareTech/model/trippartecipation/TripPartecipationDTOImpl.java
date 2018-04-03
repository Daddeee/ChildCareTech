package ChildCareTech.model.trippartecipation;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.utils.DTOFactory;

public class TripPartecipationDTOImpl implements TripPartecipationDTO {
    private PersonDTO person;
    private TripDTO trip;
    private BusDTO bus;

    public TripPartecipationDTOImpl(TripPartecipation tripPartecipation) {
        person = DTOFactory.getDTO(tripPartecipation.getPerson());
        trip = DTOFactory.getDTO(tripPartecipation.getTrip());
        bus = DTOFactory.getDTO(tripPartecipation.getBus());
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