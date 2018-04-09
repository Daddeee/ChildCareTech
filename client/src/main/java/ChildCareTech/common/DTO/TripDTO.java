package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

public class TripDTO implements Serializable {
    private String meta;
    private String note;
    private LocalDate depDate;
    private LocalDate arrDate;
    private Set<RouteDTO> routes;
    private Set<TripPartecipationDTO> tripPartecipations;

    public TripDTO(String meta, String note, LocalDate depDate, LocalDate arrDate, Set<RouteDTO> routes, Set<TripPartecipationDTO> tripPartecipations) {
        this.meta = meta;
        this.note = note;
        this.depDate = depDate;
        this.arrDate = arrDate;
        this.routes = routes == null ? Collections.EMPTY_SET : routes;
        this.tripPartecipations = tripPartecipations == null ? Collections.EMPTY_SET : tripPartecipations;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDepDate() {
        return depDate;
    }

    public void setDepDate(LocalDate depDate) {
        this.depDate = depDate;
    }

    public LocalDate getArrDate() {
        return arrDate;
    }

    public void setArrDate(LocalDate arrDate) {
        this.arrDate = arrDate;
    }

    public Set<RouteDTO> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<RouteDTO> routes) {
        this.routes = routes;
    }

    public Set<TripPartecipationDTO> getTripPartecipations() {
        return tripPartecipations;
    }

    public void setTripPartecipations(Set<TripPartecipationDTO> tripPartecipations) {
        this.tripPartecipations = tripPartecipations;
    }
}