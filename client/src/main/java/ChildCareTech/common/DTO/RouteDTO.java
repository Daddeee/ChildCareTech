package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RouteDTO implements Serializable {
    private TripDTO trip;
    private int routeNumber;
    private String departureLocation;
    private LocalDateTime departureTime;
    private String arrivalLocation;
    private LocalDateTime arrivalTime;

    public RouteDTO(TripDTO trip, int routeNumber, String departureLocation, LocalDateTime departureTime, String arrivalLocation, LocalDateTime arrivalTime) {
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
}