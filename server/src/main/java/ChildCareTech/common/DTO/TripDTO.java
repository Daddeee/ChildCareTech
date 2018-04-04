package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public class TripDTO implements Serializable {
    private String meta;
    private String note;
    private LocalDate depDate;
    private LocalDate arrDate;
    private Set<StopDTO> stops;
    private Set<TripPartecipationDTO> tripPartecipations;

    public TripDTO(String meta, String note, LocalDate depDate, LocalDate arrDate, Set<StopDTO> stops, Set<TripPartecipationDTO> tripPartecipations) {
        this.meta = meta;
        this.note = note;
        this.depDate = depDate;
        this.arrDate = arrDate;
        this.stops = stops;
        this.tripPartecipations = tripPartecipations;
    }

    public String getMeta() {
        return meta;
    }

    public String getNote() {
        return note;
    }

    public LocalDate getDepDate() {
        return depDate;
    }

    public LocalDate getArrDate() {
        return arrDate;
    }

    public Set<StopDTO> getStops() {
        return stops;
    }

    public Set<TripPartecipationDTO> getTripPartecipations() {
        return tripPartecipations;
    }
}