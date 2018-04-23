package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.model.entities.Route;
import ChildCareTech.utils.DTO.DTOFactory;

public class RouteDTOFactory implements AbstractDTOFactory<Route, RouteDTO> {
    @Override
    public RouteDTO getDTO(Route entity) {
        if(entity == null) return null;
        return getRouteDTO(entity, TripDTOFactory.getRouteOneSide(entity.getTrip()));
    }

    public static RouteDTO getTripManySide(Route entity, TripDTO tripDTO){
        return getRouteDTO(entity, tripDTO);
    }

    private static RouteDTO getRouteDTO(Route entity, TripDTO tripDTO) {
        if(entity == null)
            return null;

        return new RouteDTO(
                entity.getId(),
                tripDTO,
                entity.getRouteNumber(),
                entity.getDepartureLocation(),
                entity.getArrivalLocation(),
                entity.getStatus(),
                DTOFactory.getDTO(entity.getDepartureEvent()),
                DTOFactory.getDTO(entity.getArrivalEvent())
        );
    }
}

