package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

public interface BusDTO extends Serializable {
    String getLicensePlate();
    Set<TripPartecipationDTO> getTripPartecipations();

}