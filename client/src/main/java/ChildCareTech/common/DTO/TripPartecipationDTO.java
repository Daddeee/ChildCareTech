package ChildCareTech.common.DTO;

import java.io.Serializable;

public interface TripPartecipationDTO extends Serializable {
    PersonDTO getPerson();
    TripDTO getTrip();
    BusDTO getBus();
}