package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.utils.DTO.DTOFactoryFacade;

public class TripPartecipationDTOFactory implements AbstractDTOFactory<TripPartecipation, TripPartecipationDTO> {
    @Override
    public TripPartecipationDTO getDTO(TripPartecipation entity) {
        if(entity == null) return null;
        return getTripPartecipationDTO(
                entity,
                TripDTOFactory.getTripPartecipationOneSide(entity.getTrip()),
                BusDTOFactory.getTripPartecipationOneSide(entity.getBus())
        );
    }

    public static TripPartecipationDTO getBusManySide(TripPartecipation entity, BusDTO busDTO){
        if (entity == null)return null;
        return getTripPartecipationDTO(
                entity,
                TripDTOFactory.getTripPartecipationOneSide(entity.getTrip()),
                busDTO
        );
    }

    public static TripPartecipationDTO getTripManySide(TripPartecipation entity, TripDTO tripDTO){
        if(entity == null) return null;
        return getTripPartecipationDTO(
                entity,
                tripDTO,
                BusDTOFactory.getTripPartecipationOneSide(entity.getBus())
        );
    }

    private static TripPartecipationDTO getTripPartecipationDTO(TripPartecipation entity, TripDTO tripDTO, BusDTO busDTO) {
        if (entity == null)
            return null;

        return new TripPartecipationDTO(
                entity.getId(),
                DTOFactoryFacade.getDTO(entity.getPerson()),
                tripDTO,
                busDTO
        );
    }
}

