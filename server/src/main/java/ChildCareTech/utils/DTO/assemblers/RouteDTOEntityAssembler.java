package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.model.entities.Route;
import ChildCareTech.model.entities.Trip;

public class RouteDTOEntityAssembler implements AbstractDTOEntityAssembler<Route, RouteDTO> {
    @Override
    public Route assemble(RouteDTO dto) {
        if(dto == null)
            return null;

        return new Route(
                TripDTOEntityAssembler.assembleRouteOneSide(dto.getTrip()),
                dto.getRouteNumber(),
                dto.getDepartureLocation(),
                dto.getArrivalLocation()
        );
    }

    public static Route assembleTripManySide(RouteDTO dto, Trip trip){
        if(dto == null)
            return null;

        return new Route(
                trip,
                dto.getRouteNumber(),
                dto.getDepartureLocation(),
                dto.getArrivalLocation()
        );
    }
}
