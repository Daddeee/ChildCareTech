package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.route.Route;
import ChildCareTech.model.trip.Trip;
import ChildCareTech.model.trippartecipation.TripPartecipation;
import ChildCareTech.utils.DTO.DTOFactory;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TripDTOFactory implements AbstractDTOFactory<Trip, TripDTO> {
    @Override
    public TripDTO getDTO(Trip entity) {
        if (entity == null)
            return null;

        TripDTO dto = new TripDTO(
          entity.getMeta(),
          entity.getNote(),
          entity.getDepDate(),
          entity.getArrDate(),
          null,
          null
        );

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
        if (entity == null)
            return null;

        TripDTO dto = new TripDTO(
                entity.getMeta(),
                entity.getNote(),
                entity.getDepDate(),
                entity.getArrDate(),
                null,
                null
        );

        Set<TripPartecipationDTO> tripPartecipations = new HashSet<>();
        for (TripPartecipation t : entity.getTripPartecipations())
            tripPartecipations.add(DTOFactory.getDTO(t));
        dto.setTripPartecipations(tripPartecipations);

        return dto;
    }

    public static TripDTO getTripPartecipationOneSide(Trip entity){
        if (entity == null)
            return null;

        TripDTO dto = new TripDTO(
                entity.getMeta(),
                entity.getNote(),
                entity.getDepDate(),
                entity.getArrDate(),
                null,
                null
        );

        Set<RouteDTO> routes = new HashSet<>();
        for (Route s : entity.getRoutes())
            routes.add(RouteDTOFactory.getTripManySide(s, dto));
        dto.setRoutes(routes);

        return dto;
    }
}

