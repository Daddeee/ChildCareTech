package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class BusDTO implements Serializable {
    private int id;
    private String licensePlate;
    private Set<TripPartecipationDTO> tripPartecipations;
    private Set<TripDTO> trips;
    private int capacity;

    public BusDTO(int id, String licensePlate, Set<TripPartecipationDTO> tripPartecipations, Set<TripDTO> trips, int capacity) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.tripPartecipations = tripPartecipations == null ? Collections.EMPTY_SET : tripPartecipations;
        this.trips = trips == null ? Collections.EMPTY_SET : trips;
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

    public Set<TripDTO> getTrips() {
        return trips;
    }

    public void setTrips(Set<TripDTO> trips) {
        this.trips = trips;
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

    @Override
    public String toString() {
        return licensePlate;
    }
}