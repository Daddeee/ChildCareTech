package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

public class BusDTO implements Serializable {
    private String licensePlate;
    private Set<TripPartecipationDTO> tripPartecipations;

    public BusDTO(String licensePlate, Set<TripPartecipationDTO> tripPartecipations) {
        this.licensePlate = licensePlate;
        this.tripPartecipations = tripPartecipations;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Set<TripPartecipationDTO> getTripPartecipations() {
        return tripPartecipations;
    }
}