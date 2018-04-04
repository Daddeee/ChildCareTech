package ChildCareTech.common.DTO;

import ChildCareTech.model.stop.Stop;
import ChildCareTech.utils.DTO.DTOFactory;

import java.io.Serializable;

public class StopDTO implements Serializable {
    private TripDTO trip;
    private int stopNumber;

    public StopDTO(TripDTO trip, int stopNumber){
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