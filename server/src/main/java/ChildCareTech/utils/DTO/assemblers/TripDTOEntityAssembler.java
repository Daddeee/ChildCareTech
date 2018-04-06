package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.model.trip.Trip;

public class TripDTOEntityAssembler extends AbstractDTOEntityAssembler<Trip, TripDTO> {
    @Override
    public Trip assembleWithoutRelations(TripDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Trip entity, TripDTO dto) {

    }
}
