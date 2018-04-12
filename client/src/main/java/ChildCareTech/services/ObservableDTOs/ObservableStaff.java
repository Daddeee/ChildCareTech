package ChildCareTech.services.ObservableDTOs;

import ChildCareTech.common.DTO.StaffDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.format.DateTimeFormatter;

public class ObservableStaff extends StaffDTO implements ObservablePersonInterface<StaffDTO>{
    public ObservableStaff(StaffDTO staffDTO) {
        super(staffDTO.getId(), staffDTO.getPerson(), staffDTO.getContacts());
    }
    public StringProperty firstNameProperty() {
        return new SimpleStringProperty(getPerson().getFirstName());
    }
    public StringProperty lastNameProperty() {
        return new SimpleStringProperty(getPerson().getLastName());
    }
    public StringProperty fiscalCodeProperty() {
        return new SimpleStringProperty(getPerson().getFiscalCode());
    }
    public StringProperty birthDateProperty() {
        DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return new SimpleStringProperty(getPerson().getBirthDate().format(myDateFormatter));
    }
    public StringProperty addressProperty() {
        return new SimpleStringProperty(getPerson().getAddress());
    }
    public StringProperty roleProperty() { return new SimpleStringProperty("personale"); }
    public StaffDTO getDTO() { return new StaffDTO(getId(), getPerson(), getContacts()); }
}
