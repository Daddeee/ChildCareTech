package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.model.entities.Route;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

public class RouteDTOEntityAssembler implements AbstractDTOEntityAssembler<Route, RouteDTO> {
    @Override
    public Route assemble(RouteDTO dto) {
        return getRoute(dto, TripDTOEntityAssembler.assembleRouteOneSide(dto.getTrip()));
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
                DTOEntityAssembler.getEntity(dto.getDepartureEvent()),
                DTOEntityAssembler.getEntity(dto.getDepartureEvent())
        );
    }
}
