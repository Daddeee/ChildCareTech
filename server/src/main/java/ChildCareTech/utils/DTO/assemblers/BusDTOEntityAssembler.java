package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.TripPartecipation;

import java.util.HashSet;
import java.util.Set;

public class BusDTOEntityAssembler implements AbstractDTOEntityAssembler<Bus, BusDTO> {
    @Override
    public Bus assemble(BusDTO dto) {
        if(dto == null)
            return null;

        Bus entity = new Bus(
                dto.getLicensePlate(),
                dto.getCapacity()
        );

        Set<TripPartecipation> tripPartecipations = new HashSet<>();
        for(TripPartecipationDTO t : dto.getTripPartecipations())
            tripPartecipations.add(TripPartecipationDTOEntityAssembler.assembleBusManySide(t, entity));
        entity.setTripPartecipations(tripPartecipations);

        return entity;
    }

    public static Bus assembleTripPartecipationOneSide(BusDTO dto) {
        if(dto == null)
            return null;

        Bus entity = new Bus(
                dto.getLicensePlate(),
                dto.getCapacity()
        );

        return entity;
    }
}
