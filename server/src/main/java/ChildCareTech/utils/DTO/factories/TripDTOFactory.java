package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.entities.Route;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class TripDTOFactory implements AbstractDTOFactory<Trip, TripDTO> {
    @Override
    public TripDTO getDTO(Trip entity) {
        TripDTO dto = getTripDTO(entity);
        if (dto == null) return null;

        Set<RouteDTO> routes = new HashSet<>();
        for (Route s : entity.getRoutes())
            routes.add(RouteDTOFactory.getTripManySide(s, dto));
        dto.setRoutes(routes);

        Set<TripPartecipationDTO> tripPartecipations = new HashSet<>();
        for (TripPartecipation t : entity.getTripPartecipations())
            tripPartecipations.add(TripPartecipationDTOFactory.getTripManySide(t, dto));
        dto.setTripPartecipations(tripPartecipations);

        return dto;
    }

    public static TripDTO getRouteOneSide(Trip entity){
        TripDTO dto = getTripDTO(entity);
        if (dto == null) return null;

        Set<TripPartecipationDTO> tripPartecipations = new HashSet<>();
        for (TripPartecipation t : entity.getTripPartecipations())
            tripPartecipations.add(DTOFactory.getDTO(t));
        dto.setTripPartecipations(tripPartecipations);

        return dto;
    }

    public static TripDTO getTripPartecipationOneSide(Trip entity){
        TripDTO dto = getTripDTO(entity);
        if (dto == null) return null;

        Set<RouteDTO> routes = new HashSet<>();
        for (Route s : entity.getRoutes())
            routes.add(RouteDTOFactory.getTripManySide(s, dto));
        dto.setRoutes(routes);

        return dto;
    }

    private static TripDTO getTripDTO(Trip entity) {
        if (entity == null)
            return null;

        TripDTO dto = new TripDTO(
                entity.getId(),
                entity.getMeta(),
                entity.getNote(),
                entity.getDepDate(),
                entity.getArrDate(),
                null,
                null
        );
        return dto;
    }
}

