package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RouteDTO implements Serializable {
    private int id;
    private TripDTO trip;
    private int routeNumber;
    private String departureLocation;
    private String arrivalLocation;

    public RouteDTO(int id, TripDTO trip, int routeNumber, String departureLocation, String arrivalLocation) {
        this.id = id;
        this.trip = trip;
        this.routeNumber = routeNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
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
}