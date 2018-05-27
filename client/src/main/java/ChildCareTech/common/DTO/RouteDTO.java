package ChildCareTech.common.DTO;

import ChildCareTech.common.EventStatus;

import java.io.Serializable;

/**
 * This class provides a Data Transfer Object encapsulation for {@link ChildCareTech.model.entities.Route Route} entity.
 */
public class RouteDTO implements Serializable {
    private int id;
    private TripDTO trip;
    private int routeNumber;
    private String departureLocation;
    private String arrivalLocation;
    private EventStatus status;
    private EventDTO departureEvent;
    private EventDTO arrivalEvent;

    public RouteDTO(int id, TripDTO trip, int routeNumber, String departureLocation, String arrivalLocation, EventStatus status, EventDTO departureEvent, EventDTO arrivalEvent) {
        this.id = id;
        this.trip = trip;
        this.routeNumber = routeNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.status = status;
        this.departureEvent = departureEvent;
        this.arrivalEvent = arrivalEvent;
    }

    public int getId() {
        return id;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public EventDTO getDepartureEvent() {
        return departureEvent;
    }

    public void setDepartureEvent(EventDTO departureEvent) {
        this.departureEvent = departureEvent;
    }

    public EventDTO getArrivalEvent() {
        return arrivalEvent;
    }

    public void setArrivalEvent(EventDTO arrivalEvent) {
        this.arrivalEvent = arrivalEvent;
    }
}