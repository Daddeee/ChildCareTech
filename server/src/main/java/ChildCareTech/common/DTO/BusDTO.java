package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.Set;

public interface BusDTO extends Serializable, Remote {
    String getLicensePlate();
    Set<TripPartecipationDTO> getTripPartecipations();
}