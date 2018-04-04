package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.model.route.Route;
import ChildCareTech.utils.DTO.DTOFactory;

import java.time.LocalDateTime;

public class RouteDTOFactory implements AbstractDTOFactory<Route, RouteDTO> {
    @Override
    public RouteDTO getDTO(Route entity) {
        if (entity == null)
            return null;

        TripDTO trip = DTOFactory.getDTO(entity.getTrip());
        int routeNumber = entity.getRouteNumber();
        String departureLocationLocation = entity.getDepartureLocation();
        LocalDateTime departureTime = entity.getDepartureTime();
        String arrivalLocation = entity.getArrivalLocation();
        LocalDateTime arrivalTime = entity.getArrivalTime();

        return new RouteDTO(trip, routeNumber, departureLocationLocation, departureTime, arrivalLocation, arrivalTime);
    }
}

