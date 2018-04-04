package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Set;

public class BusDTO implements Serializable {
    private String licensePlate;
    private Set<TripPartecipationDTO> tripPartecipations;
    private int capacity;

    public BusDTO(String licensePlate, Set<TripPartecipationDTO> tripPartecipations, int capacity) {
        this.licensePlate = licensePlate;
        this.tripPartecipations = tripPartecipations;
        this.capacity = capacity;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Set<TripPartecipationDTO> getTripPartecipations() {
        return tripPartecipations;
    }

    public int getCapacity() {
        return capacity;
    }
}