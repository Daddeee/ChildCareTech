package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RouteDTO implements Serializable {
    private TripDTO trip;
    private int routeNumber;
    private String departureLocation;
    private String arrivalLocation;

    public RouteDTO(TripDTO trip, int routeNumber, String departureLocation, String arrivalLocation) {
        this.trip = trip;
        this.routeNumber = routeNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
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

    public String getArrivalLocation() {
        return arrivalLocation;
    }
}