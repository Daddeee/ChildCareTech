package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.rmi.Remote;
import java.time.LocalDate;
import java.util.Set;

public interface TripDTO extends Serializable, Remote {
    String getMeta();
    String getNote();
    LocalDate getDepDate();
    LocalDate getArrDate();
    Set<StopDTO> getStops();
    Set<TripPartecipationDTO> getTripPartecipations();
}