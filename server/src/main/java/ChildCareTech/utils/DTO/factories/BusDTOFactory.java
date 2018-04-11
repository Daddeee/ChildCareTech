package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.TripPartecipation;

import java.util.HashSet;
import java.util.Set;

public class BusDTOFactory implements AbstractDTOFactory<Bus, BusDTO> {
    @Override
    public BusDTO getDTO(Bus entity) {
        BusDTO dto = getBusDTO(entity);
        if (dto == null) return null;

        Set<TripPartecipationDTO> tripPartecipations = new HashSet<>();
        for (TripPartecipation t : entity.getTripPartecipations())
            tripPartecipations.add(TripPartecipationDTOFactory.getBusManySide(t, dto));
        dto.setTripPartecipations(tripPartecipations);

        return dto;
    }

    private static BusDTO getBusDTO(Bus entity) {
        if (entity == null)
            return null;

        return new BusDTO(
                entity.getId(),
                entity.getLicensePlate(),
                null,
                entity.getCapacity()
        );
    }

    public static BusDTO getTripPartecipationOneSide(Bus entity){
        return getBusDTO(entity);
    }
}

