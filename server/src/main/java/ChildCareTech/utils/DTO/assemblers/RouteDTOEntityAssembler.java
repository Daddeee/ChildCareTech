package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.model.route.Route;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

public class RouteDTOEntityAssembler implements AbstractDTOEntityAssembler<Route, RouteDTO> {
    @Override
    public Route assemble(RouteDTO dto) {
        if(dto == null)
            return null;

        return new Route(
                DTOEntityAssembler.getEntity(dto.getTrip()),
                dto.getRouteNumber(),
                dto.getDepartureLocation(),
                dto.getArrivalLocation()
        );
    }
}
