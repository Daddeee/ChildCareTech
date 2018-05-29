package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.TripPartecipation;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

public class BusDTOFactory implements AbstractDTOFactory<Bus, BusDTO> {
    @Override
    public BusDTO getDTO(Bus entity) {
        BusDTO dto = getBusDTO(entity);
        if (dto == null) return null;

        loadTripPartecipationRelationship(entity, dto);
        loadTripRelationship(entity, dto);

        return dto;
    }

    private static BusDTO getBusDTO(Bus entity) {
        if (entity == null)
            return null;

        return new BusDTO(
                entity.getId(),
                entity.getLicensePlate(),
                null,
                null,
                entity.getCapacity()
        );
    }


    public static BusDTO getTripManySide(Bus entity) {
        BusDTO dto = getBusDTO(entity);
        if (dto == null) return null;

        loadTripPartecipationRelationship(entity, dto);

        return dto;
    }

    public static BusDTO getTripPartecipationOneSide(Bus entity){
        return getBusDTO(entity);
    }

    private static void loadTripPartecipationRelationship(Bus entity, BusDTO dto) {
        Set<TripPartecipationDTO> tripPartecipations = new HashSet<>();

        if(Hibernate.isInitialized(entity.getTripPartecipations()))
            for (TripPartecipation t : entity.getTripPartecipations())
                tripPartecipations.add(TripPartecipationDTOFactory.getBusManySide(t, dto));

        dto.setTripPartecipations(tripPartecipations);
    }

    private void loadTripRelationship(Bus entity, BusDTO dto) {
        Set<TripDTO> trips = new HashSet<>();

        if(Hibernate.isInitialized(entity.getTrips()) && entity.getTrips() != null)
            for(Trip t : entity.getTrips())
                trips.add(TripDTOFactory.getBusManySide(t));

        dto.setTrips(trips);
    }
}

