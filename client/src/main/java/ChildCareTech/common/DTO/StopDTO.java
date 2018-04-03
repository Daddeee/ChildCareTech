package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.rmi.Remote;

public interface StopDTO extends Serializable, Remote {
    TripDTO getTrip();
    int getStopNumber();
}