package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.trippartecipation.TripPartecipation;
import ChildCareTech.utils.DTO.DTOFactory;

public class TripPartecipationDTOFactory implements AbstractDTOFactory<TripPartecipation, TripPartecipationDTO> {
    @Override
    public TripPartecipationDTO getDTO(TripPartecipation entity) {
        if(entity == null)
            return null;

        PersonDTO person = DTOFactory.getDTO(entity.getPerson());
        TripDTO trip = DTOFactory.getDTO(entity.getTrip());
        BusDTO bus = DTOFactory.getDTO(entity.getBus());

        return new TripPartecipationDTO(person, trip, bus);
    }
}

