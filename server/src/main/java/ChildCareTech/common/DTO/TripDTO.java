package ChildCareTech.common.DTO;

import ChildCareTech.common.EventStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
/**
 * This class provides a Data Transfer Object encapsulation for {@link ChildCareTech.model.entities.Trip Trip} entity.
 */
public class TripDTO implements Serializable {
    private int id;
    private String meta;
    private String note;
    private LocalDate depDate;
    private LocalDate arrDate;
    private EventStatus status;
    private Set<RouteDTO> routes;
    private Set<TripPartecipationDTO> tripPartecipations;
    private Set<BusDTO> buses;

    public TripDTO(int id, String meta, String note, LocalDate depDate, LocalDate arrDate, EventStatus status, Set<RouteDTO> routes, Set<TripPartecipationDTO> tripPartecipations, Set<BusDTO> buses) {
        this.id = id;
        this.meta = meta;
        this.note = note;
        this.depDate = depDate;
        this.arrDate = arrDate;
        this.status = status;
        this.routes = routes == null ? Collections.EMPTY_SET : routes;
        this.tripPartecipations = tripPartecipations == null ? Collections.EMPTY_SET : tripPartecipations;
        this.buses = buses == null ? Collections.EMPTY_SET : buses;
    }

    public int getId(){
        return id;
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

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
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

    public Set<BusDTO> getBuses() {
        return buses;
    }

    public void setBuses(Set<BusDTO> buses) {
        this.buses = buses;
    }
}