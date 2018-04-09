package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.bus.Bus;
import ChildCareTech.model.trip.Trip;
import ChildCareTech.model.trippartecipation.TripPartecipation;
import ChildCareTech.utils.DTO.DTOEntityAssembler;

public class TripPartecipationDTOEntityAssembler implements AbstractDTOEntityAssembler<TripPartecipation, TripPartecipationDTO> {
    @Override
    public TripPartecipation assemble(TripPartecipationDTO dto) {
        if(dto == null)
            return null;

        return new TripPartecipation(
                DTOEntityAssembler.getEntity(dto.getPerson()),
                TripDTOEntityAssembler.assembleTripPartecipationOneSide(dto.getTrip()),
                BusDTOEntityAssembler.assembleTripPartecipationOneSide(dto.getBus())
        );
    }

    public static TripPartecipation assembleBusManySide(TripPartecipationDTO dto, Bus bus){
        if(dto == null)
            return null;

        return new TripPartecipation(
                DTOEntityAssembler.getEntity(dto.getPerson()),
                DTOEntityAssembler.getEntity(dto.getTrip()),
                bus
        );
    }

    public static TripPartecipation assembleTripManySide(TripPartecipationDTO dto, Trip trip){
        if(dto == null)
            return null;

        return new TripPartecipation(
                DTOEntityAssembler.getEntity(dto.getPerson()),
                trip,
                BusDTOEntityAssembler.assembleTripPartecipationOneSide(dto.getBus())
        );
    }
}
