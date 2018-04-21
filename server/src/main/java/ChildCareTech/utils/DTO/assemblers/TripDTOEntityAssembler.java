package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Route;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

import java.util.HashSet;
import java.util.Set;

public class TripDTOEntityAssembler implements AbstractDTOEntityAssembler<Trip, TripDTO> {
    @Override
    public Trip assemble(TripDTO dto) {
        Trip entity = getTrip(dto);
        if (entity == null) return null;

        Set<Route> routes = new HashSet<>();
        for(RouteDTO r : dto.getRoutes())
            routes.add(RouteDTOEntityAssembler.assembleTripManySide(r, entity));
        entity.setRoutes(routes);

        Set<TripPartecipation> tripPartecipations = new HashSet<>();
        for(TripPartecipationDTO t : dto.getTripPartecipations())
            tripPartecipations.add(TripPartecipationDTOEntityAssembler.assembleTripManySide(t, entity));
        entity.setTripPartecipations(tripPartecipations);

        Set<Bus> buses = new HashSet<>();
        for(BusDTO b : dto.getBuses())
            buses.add(BusDTOEntityAssembler.assembleTripManySide(b));
        entity.setBuses(buses);

        return entity;
    }

    public static Trip assembleRouteOneSide(TripDTO dto){
        Trip entity = getTrip(dto);
        if (entity == null) return null;

        Set<TripPartecipation> tripPartecipations = new HashSet<>();
        for(TripPartecipationDTO t : dto.getTripPartecipations())
            tripPartecipations.add(DTOEntityAssembler.getEntity(t));
        entity.setTripPartecipations(tripPartecipations);

        return entity;
    }

    public static Trip assembleTripPartecipationOneSide(TripDTO dto){
        Trip entity = getTrip(dto);
        if (entity == null) return null;

        Set<Route> routes = new HashSet<>();
        for(RouteDTO r : dto.getRoutes())
            routes.add(RouteDTOEntityAssembler.assembleTripManySide(r, entity));
        entity.setRoutes(routes);

        return entity;
    }

    public static Trip assembleBusManySide(TripDTO dto){
        Trip entity = getTrip(dto);
        if (entity == null) return null;

        Set<Route> routes = new HashSet<>();
        for(RouteDTO r : dto.getRoutes())
            routes.add(RouteDTOEntityAssembler.assembleTripManySide(r, entity));
        entity.setRoutes(routes);

        Set<TripPartecipation> tripPartecipations = new HashSet<>();
        for(TripPartecipationDTO t : dto.getTripPartecipations())
            tripPartecipations.add(TripPartecipationDTOEntityAssembler.assembleTripManySide(t, entity));
        entity.setTripPartecipations(tripPartecipations);

        return entity;
    }

    private static Trip getTrip(TripDTO dto) {
        if(dto == null)
            return null;

        Trip entity = new Trip(
                dto.getId(),
                dto.getMeta(),
                dto.getNote(),
                dto.getDepDate(),
                dto.getArrDate(),
                dto.getStatus()
        );
        return entity;
    }
}
