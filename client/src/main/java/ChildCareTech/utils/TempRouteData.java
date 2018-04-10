package ChildCareTech.utils;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;

public class TempRouteData{
    public int routeNumber;
    public String departureLocation;
    public String arrivalLocation;

    public TempRouteData(int routeNumber, String departureLocation, String arrivalLocation){
        this.routeNumber = routeNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
    }

    public int getRouteNumber() { return routeNumber; }
    public String getDepartureLocation() { return departureLocation; }
    public String getArrivalLocation() { return arrivalLocation; }

    public RouteDTO getRouteDTO(TripDTO t) { return new RouteDTO(t, routeNumber, departureLocation, arrivalLocation); }
}
