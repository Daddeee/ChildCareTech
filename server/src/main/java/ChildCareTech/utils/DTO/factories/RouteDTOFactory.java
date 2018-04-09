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

        return new RouteDTO(
                DTOFactory.getDTO(entity.getTrip()),
                entity.getRouteNumber(),
                entity.getDepartureLocation(),
                entity.getArrivalLocation()
        );
    }

    public static RouteDTO getDTOTripManySide(Route entity, TripDTO tripDTO){
        if(entity == null)
            return null;

        return new RouteDTO(
                tripDTO,
                entity.getRouteNumber(),
                entity.getDepartureLocation(),
                entity.getArrivalLocation()
        );
    }
}

