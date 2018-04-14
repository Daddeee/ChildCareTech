package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.TripPartecipation;

import java.util.HashSet;
import java.util.Set;

public class BusDTOEntityAssembler implements AbstractDTOEntityAssembler<Bus, BusDTO> {
    @Override
    public Bus assemble(BusDTO dto) {
        Bus entity = getBus(dto);
        if(entity == null) return null;

        Set<TripPartecipation> tripPartecipations = new HashSet<>();
        for(TripPartecipationDTO t : dto.getTripPartecipations())
            tripPartecipations.add(TripPartecipationDTOEntityAssembler.assembleBusManySide(t, entity));
        entity.setTripPartecipations(tripPartecipations);

        Set<Trip> trips = new HashSet<>();
        for(TripDTO t : dto.getTrips())
            trips.add(TripDTOEntityAssembler.assembleBusManySide(t));
        entity.setTrips(trips);

        return entity;
    }

    public static Bus assembleTripManySide(BusDTO dto) {
        Bus entity = getBus(dto);
        if(entity == null) return null;

        Set<TripPartecipation> tripPartecipations = new HashSet<>();
        for(TripPartecipationDTO t : dto.getTripPartecipations())
            tripPartecipations.add(TripPartecipationDTOEntityAssembler.assembleBusManySide(t, entity));
        entity.setTripPartecipations(tripPartecipations);

        return entity;
    }

    public static Bus assembleTripPartecipationOneSide(BusDTO dto) {
        return getBus(dto);
    }

    private static Bus getBus(BusDTO dto) {
        if(dto == null)
            return null;

        Bus entity = new Bus(
                dto.getId(),
                dto.getLicensePlate(),
                dto.getCapacity()
        );

        return entity;
    }
}
