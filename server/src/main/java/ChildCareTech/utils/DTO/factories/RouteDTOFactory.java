package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.model.entities.Route;

public class RouteDTOFactory implements AbstractDTOFactory<Route, RouteDTO> {
    @Override
    public RouteDTO getDTO(Route entity) {
        if (entity == null)
            return null;

        return new RouteDTO(
                entity.getId(),
                TripDTOFactory.getRouteOneSide(entity.getTrip()),
                entity.getRouteNumber(),
                entity.getDepartureLocation(),
                entity.getArrivalLocation()
        );
    }

    public static RouteDTO getTripManySide(Route entity, TripDTO tripDTO){
        if(entity == null)
            return null;

        return new RouteDTO(
                entity.getId(),
                tripDTO,
                entity.getRouteNumber(),
                entity.getDepartureLocation(),
                entity.getArrivalLocation()
        );
    }
}

