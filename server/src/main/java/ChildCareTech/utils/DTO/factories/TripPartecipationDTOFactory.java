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
        if (entity == null)
            return null;

        return new TripPartecipationDTO(DTOFactory.getDTO(
                entity.getPerson()),
                TripDTOFactory.getTripPartecipationOneSide(entity.getTrip()),
                DTOFactory.getDTO(entity.getBus())
        );
    }

    public static TripPartecipationDTO getBusManySide(TripPartecipation entity, BusDTO busDTO){
        if (entity == null)
            return null;

        return new TripPartecipationDTO(DTOFactory.getDTO(
                entity.getPerson()),
                TripDTOFactory.getTripPartecipationOneSide(entity.getTrip()),
                busDTO
        );
    }

    public static TripPartecipationDTO getTripManySide(TripPartecipation entity, TripDTO tripDTO){
        if (entity == null)
            return null;

        return new TripPartecipationDTO(DTOFactory.getDTO(
                entity.getPerson()),
                TripDTOFactory.getTripPartecipationOneSide(entity.getTrip()),
                DTOFactory.getDTO(entity.getBus())
        );
    }
}

