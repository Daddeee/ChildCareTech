package ChildCareTech.model.bus;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.model.trippartecipation.TripPartecipation;
import ChildCareTech.model.trippartecipation.TripPartecipationDTOImpl;

import java.util.HashSet;
import java.util.Set;

public class BusDTOImpl implements BusDTO {
    private String licensePlate;
    private Set<TripPartecipationDTO> tripPartecipations;

    public BusDTOImpl(Bus bus){
        licensePlate = bus.getLicensePlate();
        tripPartecipations = new HashSet<>();
        for(TripPartecipation t : bus.getTripPartecipations())
            tripPartecipations.add(new TripPartecipationDTOImpl(t));
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public Set<TripPartecipationDTO> getTripPartecipations() {
        return tripPartecipations;
    }
}