package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.RouteDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Route;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.utils.DTO.DTOFactory;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

public class TripDTOFactory implements AbstractDTOFactory<Trip, TripDTO> {
    @Override
    public TripDTO getDTO(Trip entity) {
        TripDTO dto = getTripDTO(entity);
        if (dto == null) return null;

        loadRouteRelationship(entity, dto);
        loadTripPartecipationRelationship(entity, dto);
        loadBusRelationship(entity, dto);

        return dto;
    }

    public static TripDTO getRouteOneSide(Trip entity){
        TripDTO dto = getTripDTO(entity);
        if (dto == null) return null;

        loadTripPartecipationRelationship(entity, dto);

        return dto;
    }

    public static TripDTO getTripPartecipationOneSide(Trip entity){
        TripDTO dto = getTripDTO(entity);
        if (dto == null) return null;

        loadRouteRelationship(entity, dto);

        return dto;
    }

    public static TripDTO getBusManySide(Trip entity){
        TripDTO dto = getTripDTO(entity);
        if (dto == null) return null;

        loadRouteRelationship(entity, dto);

        loadTripPartecipationRelationship(entity, dto);

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
                null,
                null
        );
        return dto;
    }

    private static void loadTripPartecipationRelationship(Trip entity, TripDTO dto) {
        Set<TripPartecipationDTO> tripPartecipations = new HashSet<>();

        if(Hibernate.isInitialized(entity.getTripPartecipations()))
            for (TripPartecipation t : entity.getTripPartecipations())
                tripPartecipations.add(TripPartecipationDTOFactory.getTripManySide(t, dto));

        dto.setTripPartecipations(tripPartecipations);
    }

    private void loadBusRelationship(Trip entity, TripDTO dto) {
        Set<BusDTO> buses = new HashSet<>();

        if(Hibernate.isInitialized(entity.getBuses()))
            for(Bus b : entity.getBuses())
                buses.add(BusDTOFactory.getTripManySide(b));

        dto.setBuses(buses);
    }

    private static void loadRouteRelationship(Trip entity, TripDTO dto) {
        Set<RouteDTO> routes = new HashSet<>();

        if(Hibernate.isInitialized(entity.getRoutes()))
            for (Route s : entity.getRoutes())
                routes.add(RouteDTOFactory.getTripManySide(s, dto));
        
        dto.setRoutes(routes);
    }
}

