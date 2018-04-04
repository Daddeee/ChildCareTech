package ChildCareTech.common.DTO;

import java.io.Serializable;

public class StopDTO implements Serializable {
    private TripDTO trip;
    private int stopNumber;

    public StopDTO(TripDTO trip, int stopNumber) {
        this.trip = trip;
        this.stopNumber = stopNumber;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public int getStopNumber() {
        return stopNumber;
    }
}