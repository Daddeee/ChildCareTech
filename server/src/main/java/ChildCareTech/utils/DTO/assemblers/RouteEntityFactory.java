package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.model.entities.Route;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.utils.DTO.EntityFactoryFacade;

public class RouteEntityFactory implements AbstractEntityFactory<Route, RouteDTO> {
    @Override
    public Route assemble(RouteDTO dto) {
        return getRoute(dto, TripEntityFactory.assembleRouteOneSide(dto.getTrip()));
    }

    public static Route assembleTripManySide(RouteDTO dto, Trip trip){
        return getRoute(dto, trip);
    }

    private static Route getRoute(RouteDTO dto, Trip trip) {
        if(dto == null)
            return null;

        return new Route(
                dto.getId(),
                trip,
                dto.getRouteNumber(),
                dto.getDepartureLocation(),
                dto.getArrivalLocation(),
                dto.getStatus(),
                EntityFactoryFacade.getEntity(dto.getDepartureEvent()),
                EntityFactoryFacade.getEntity(dto.getArrivalEvent())
        );
    }
}
