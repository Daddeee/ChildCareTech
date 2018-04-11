package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class BusDTO implements Serializable {
    private int id;
    private String licensePlate;
    private Set<TripPartecipationDTO> tripPartecipations;
    private int capacity;

    public BusDTO(int id, String licensePlate, Set<TripPartecipationDTO> tripPartecipations, int capacity) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.tripPartecipations = tripPartecipations == null ? Collections.EMPTY_SET : tripPartecipations;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
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

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTripPartecipations(Set<TripPartecipationDTO> tripPartecipations) {
        this.tripPartecipations = tripPartecipations;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}