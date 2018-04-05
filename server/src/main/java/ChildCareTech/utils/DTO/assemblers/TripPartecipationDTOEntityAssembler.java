package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.trippartecipation.TripPartecipation;

public class TripPartecipationDTOEntityAssembler extends AbstractDTOEntityAssembler<TripPartecipation, TripPartecipationDTO> {
    @Override
    public TripPartecipation assembleWithoutRelations(TripPartecipationDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(TripPartecipation entity, TripPartecipationDTO dto) {

    }
}
