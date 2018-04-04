package ChildCareTech.common.DTO;

import java.io.Serializable;

public class StopDTO implements Serializable {
    private TripDTO trip;
    private int stopNumber;
    private String stopLocation;

    public StopDTO(TripDTO trip, int stopNumber, String stopLocation) {
        this.trip = trip;
        this.stopNumber = stopNumber;
        this.stopLocation = stopLocation;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public int getStopNumber() {
        return stopNumber;
    }

    public String getStopLocation() {
        return stopLocation;
    }
}