package ChildCareTech.utils;

import ChildCareTech.common.DTO.BusDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.DTO.TripPartecipationDTO;

import java.util.Set;

public class BusDTOWithResidualCapacity extends BusDTO {
    private int residualCapacity;

    public BusDTOWithResidualCapacity(int id, String licensePlate, Set<TripPartecipationDTO> tripPartecipations, Set<TripDTO> trips, int capacity, int residualCapacity){
        super(id, licensePlate, tripPartecipations, trips, capacity);
        this.residualCapacity = residualCapacity;
    }

    public int getResidualCapacity() {
        return residualCapacity;
    }

    public void setResidualCapacity(int residualCapacity) {
        this.residualCapacity = residualCapacity;
    }

    public BusDTO getBusDTO(){
        return new BusDTO(
                getId(),
                getLicensePlate(),
                getTripPartecipations(),
                getTrips(),
                getCapacity()
        );
    }

    public static BusDTOWithResidualCapacity buildFromBusDTO(BusDTO busDTO){
        return new BusDTOWithResidualCapacity(
                busDTO.getId(),
                busDTO.getLicensePlate(),
                busDTO.getTripPartecipations(),
                busDTO.getTrips(),
                busDTO.getCapacity(),
                busDTO.getCapacity()
        );
    }
}
