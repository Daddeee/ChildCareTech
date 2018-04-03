package ChildCareTech.model.stop;

import ChildCareTech.common.DTO.StopDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.model.trip.TripDTOImpl;

public class StopDTOImpl implements StopDTO {
    private TripDTO trip;
    private int stopNumber;

    public StopDTOImpl(Stop stop){
        trip = new TripDTOImpl(stop.getTrip());
        stopNumber = stop.getStopNumber();
    }

    @Override
    public TripDTO getTrip() {
        return trip;
    }

    @Override
    public int getStopNumber() {
        return stopNumber;
    }
}