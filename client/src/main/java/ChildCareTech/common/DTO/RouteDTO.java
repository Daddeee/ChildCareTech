package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalTime;

public class RouteDTO implements Serializable {
    private TripDTO trip;
    private int routeNumber;
    private String departureLocation;
    private LocalTime departureTime;
    private String arrivalLocation;
    private LocalTime arrivalTime;

    public RouteDTO(TripDTO trip, int routeNumber, String departureLocation, LocalTime departureTime, String arrivalLocation, LocalTime arrivalTime) {
        this.trip = trip;
        this.routeNumber = routeNumber;
        this.departureLocation = departureLocation;
        this.departureTime = departureTime;
        this.arrivalLocation = arrivalLocation;
        this.arrivalTime = arrivalTime;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
}