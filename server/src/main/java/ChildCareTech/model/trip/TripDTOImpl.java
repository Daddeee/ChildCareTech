package ChildCareTech.model.trip;

import ChildCareTech.common.DTO.StopDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.stop.Stop;
import ChildCareTech.model.stop.StopDTOImpl;
import ChildCareTech.model.trippartecipation.TripPartecipation;
import ChildCareTech.model.trippartecipation.TripPartecipationDTOImpl;
import ChildCareTech.utils.DTOFactory;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TripDTOImpl implements TripDTO {
    private String meta;
    private String note;
    private LocalDate depDate;
    private LocalDate arrDate;
    private Set<StopDTO> stops;
    private Set<TripPartecipationDTO> tripPartecipations;

    public TripDTOImpl(Trip trip) {
        meta = trip.getMeta();
        note = trip.getNote();
        depDate = trip.getDepDate();
        arrDate = trip.getArrDate();

        stops = new HashSet<>();
        for(Stop s : trip.getStops())
            stops.add(DTOFactory.getDTO(s));

        tripPartecipations = new HashSet<>();
        for(TripPartecipation t : trip.getTripPartecipations())
            tripPartecipations.add(DTOFactory.getDTO(t));
    }

    @Override
    public String getMeta() {
        return meta;
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public LocalDate getDepDate() {
        return depDate;
    }

    @Override
    public LocalDate getArrDate() {
        return arrDate;
    }

    @Override
    public Set<StopDTO> getStops() {
        return stops;
    }

    @Override
    public Set<TripPartecipationDTO> getTripPartecipations() {
        return tripPartecipations;
    }
}