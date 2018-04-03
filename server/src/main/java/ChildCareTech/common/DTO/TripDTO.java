package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public interface TripDTO extends Serializable {
    String getMeta();
    String getNote();
    LocalDate getDepDate();
    LocalDate getArrDate();
    Set<StopDTO> getStops();
    Set<TripPartecipationDTO> getTripPartecipations();
}