package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

public class TripPartecipationDTOEntityAssembler implements AbstractDTOEntityAssembler<TripPartecipation, TripPartecipationDTO> {
    @Override
    public TripPartecipation assemble(TripPartecipationDTO dto) {
        if(dto == null) return null;
        return getTripPartecipation(
                dto,
                TripDTOEntityAssembler.assembleTripPartecipationOneSide(dto.getTrip()),
                BusDTOEntityAssembler.assembleTripPartecipationOneSide(dto.getBus())
        );
    }

    public static TripPartecipation assembleBusManySide(TripPartecipationDTO dto, Bus bus){
        if(dto == null) return null;
        return getTripPartecipation(
                dto,
                TripDTOEntityAssembler.assembleTripPartecipationOneSide(dto.getTrip()),
                bus
        );
    }

    public static TripPartecipation assembleTripManySide(TripPartecipationDTO dto, Trip trip){
        if(dto == null) return null;
        return getTripPartecipation(
                dto,
                trip,
                BusDTOEntityAssembler.assembleTripPartecipationOneSide(dto.getBus())
        );
    }

    private static TripPartecipation getTripPartecipation(TripPartecipationDTO dto, Trip trip, Bus bus) {
        if(dto == null)
            return null;

        return new TripPartecipation(
                dto.getId(),
                DTOEntityAssembler.getEntity(dto.getPerson()),
                trip,
                bus
        );
    }
}
