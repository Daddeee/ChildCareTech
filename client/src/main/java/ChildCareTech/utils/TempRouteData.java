package ChildCareTech.utils;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.EventStatus;

public class TempRouteData{
    private int id;
    public int routeNumber;
    public String departureLocation;
    public String arrivalLocation;
    public EventStatus status;

    public TempRouteData(int id, int routeNumber, String departureLocation, String arrivalLocation, EventStatus status){
        this.id = id;
        this.routeNumber = routeNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.status = status;
    }

    public int getRouteNumber() { return routeNumber; }
    public String getDepartureLocation() { return departureLocation; }
    public String getArrivalLocation() { return arrivalLocation; }
    public EventStatus getStatus() { return status; }

    public RouteDTO getRouteDTO(TripDTO t) {
        return new RouteDTO(
                id,
                t,
                routeNumber,
                departureLocation,
                arrivalLocation,
                status
        );
    }
}
