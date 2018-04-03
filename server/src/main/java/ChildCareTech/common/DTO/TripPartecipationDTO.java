package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.rmi.Remote;

public interface TripPartecipationDTO extends Serializable, Remote {
    PersonDTO getPerson();

    TripDTO getTrip();

    BusDTO getBus();
}