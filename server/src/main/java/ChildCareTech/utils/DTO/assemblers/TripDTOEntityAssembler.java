package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.route.Route;
import ChildCareTech.model.trip.Trip;
import ChildCareTech.model.trippartecipation.TripPartecipation;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class TripDTOEntityAssembler implements AbstractDTOEntityAssembler<Trip, TripDTO> {
    @Override
    public Trip assemble(TripDTO dto) {
        if(dto == null)
            return null;

        Trip entity = new Trip(
                dto.getMeta(),
                dto.getNote(),
                dto.getDepDate(),
                dto.getArrDate()
        );

        Set<Route> routes = new HashSet<>();
        for(RouteDTO r : dto.getRoutes())
            routes.add(DTOEntityAssembler.getEntity(r));
        entity.setRoutes(routes);

        Set<TripPartecipation> tripPartecipations = new HashSet<>();
        for(TripPartecipationDTO t : dto.getTripPartecipations())
            tripPartecipations.add(DTOEntityAssembler.getEntity(t));
        entity.setTripPartecipations(tripPartecipations);

        return entity;
    }
}
