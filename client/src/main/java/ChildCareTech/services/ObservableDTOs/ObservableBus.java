package ChildCareTech.services.ObservableDTOs;

import ChildCareTech.common.DTO.BusDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;


public class ObservableBus extends BusDTO implements ObservableAnagraphic{
    public ObservableBus(BusDTO busDTO){
        super(
                busDTO.getId(),
                busDTO.getLicensePlate(),
                busDTO.getTripPartecipations(),
                busDTO.getCapacity()
        );
    }

    public StringProperty licensePlateProperty() { return new SimpleStringProperty(getLicensePlate()); }
    public StringProperty capacityProperty() { return new SimpleStringProperty(String.valueOf(getCapacity())); }

    public static Map<String, String> getProperties() {
        Map<String, String> properties = new HashMap<>();

        properties.put("Targa", "licensePlate");
        properties.put("Capienza", "capacity");

        return properties;
    }

    public BusDTO getDTO() {
        return new BusDTO(
                getId(),
                getLicensePlate(),
                getTripPartecipations(),
                getCapacity()
        );
    }
}
