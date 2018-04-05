package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.model.route.Route;

public class RouteDTOEntityAssembler extends AbstractDTOEntityAssembler<Route, RouteDTO> {
    @Override
    public Route assembleWithoutRelations(RouteDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Route entity, RouteDTO dto) {

    }
}
