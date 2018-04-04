package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.StopDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.stop.Stop;
import ChildCareTech.model.trip.Trip;
import ChildCareTech.model.trippartecipation.TripPartecipation;
import ChildCareTech.utils.DTO.DTOFactory;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TripDTOFactory implements AbstractDTOFactory<Trip, TripDTO> {
    @Override
    public TripDTO getDTO(Trip entity) {
        if(entity == null)
            return null;

        String meta = entity.getMeta();
        String note = entity.getNote();
        LocalDate depDate = entity.getDepDate();
        LocalDate arrDate = entity.getArrDate();

        Set<StopDTO> stops = new HashSet<>();
        for(Stop s  : entity.getStops())
            stops.add(DTOFactory.getDTO(s));

        Set<TripPartecipationDTO> tripPartecipations = new HashSet<>();
        for(TripPartecipation t : entity.getTripPartecipations())
            tripPartecipations.add(DTOFactory.getDTO(t));

        return new TripDTO(meta, note, depDate, arrDate, stops, tripPartecipations);
    }
}

