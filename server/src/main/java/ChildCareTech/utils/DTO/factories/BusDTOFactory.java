package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.bus.Bus;
import ChildCareTech.model.trippartecipation.TripPartecipation;
import ChildCareTech.utils.DTO.DTOFactory;

import java.util.HashSet;
import java.util.Set;

public class BusDTOFactory implements AbstractDTOFactory<Bus, BusDTO> {
    @Override
    public BusDTO getDTO(Bus entity) {
        if (entity == null)
            return null;

        BusDTO dto = new BusDTO(
                entity.getLicensePlate(),
                null,
                entity.getCapacity()
        );

        Set<TripPartecipationDTO> tripPartecipations = new HashSet<>();
        for (TripPartecipation t : entity.getTripPartecipations())
            tripPartecipations.add(TripPartecipationDTOFactory.getBusManySide(t, dto));
        dto.setTripPartecipations(tripPartecipations);

        return dto;
    }

    public static BusDTO getTripPartecipationOneSide(Bus entity){
        if (entity == null)
            return null;

        return new BusDTO(
                entity.getLicensePlate(),
                null,
                entity.getCapacity()
        );
    }
}

