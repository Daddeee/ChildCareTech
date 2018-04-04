package ChildCareTech.utils.DTO.factories;

import ChildCareTech.common.DTO.StopDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.model.stop.Stop;
import ChildCareTech.utils.DTO.DTOFactory;

public class StopDTOFactory implements AbstractDTOFactory<Stop, StopDTO> {
    @Override
    public StopDTO getDTO(Stop entity) {
        if(entity == null)
            return null;

        TripDTO trip = DTOFactory.getDTO(entity.getTrip());
        int stopNumber = entity.getStopNumber();

        return new StopDTO(trip, stopNumber);
    }
}

