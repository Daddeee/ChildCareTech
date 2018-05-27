package ChildCareTech.utils;

import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.EventStatus;
/**
 * This class is used for simplify the {@link RouteDTO RouteDTO} instance's display in tables.
 */
public class TempRouteData{
    private int id;
    public int routeNumber;
    public String departureLocation;
    public String arrivalLocation;
    public EventStatus status;
    public EventDTO departureEvent;
    public EventDTO arrivalEvent;

    public TempRouteData(int id, int routeNumber, String departureLocation, String arrivalLocation, EventStatus status, EventDTO departureEvent, EventDTO arrivalEvent){
        this.id = id;
        this.routeNumber = routeNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.status = status;
        this.departureEvent = departureEvent;
        this.arrivalEvent = arrivalEvent;
    }

    public int getRouteNumber() { return routeNumber; }
    public String getDepartureLocation() { return departureLocation; }
    public String getArrivalLocation() { return arrivalLocation; }
    public EventStatus getStatus() { return status; }

    public EventDTO getDepartureEvent() {
        return departureEvent;
    }

    public EventDTO getArrivalEvent() {
        return arrivalEvent;
    }

    public RouteDTO getRouteDTO(TripDTO t) {
        return new RouteDTO(
                id,
                t,
                routeNumber,
                departureLocation,
                arrivalLocation,
                status,
                departureEvent,
                arrivalEvent
        );
    }
}
