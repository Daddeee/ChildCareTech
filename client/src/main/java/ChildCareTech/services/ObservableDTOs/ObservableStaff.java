package ChildCareTech.services.ObservableDTOs;

import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.common.Sex;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * This class provides a wrapper for the {@link StaffDTO StaffDTO} class.
 * The purpose is to simplify StaffDTO instances' display in tables.
 */
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

    public String getFirstName() {
        return this.getPerson().getFirstName();
    }
    public String getLastName() {
        return this.getPerson().getLastName();
    }
    public String getFiscalCode() {
        return this.getPerson().getFiscalCode();
    }
    public LocalDate getBirthDate() {
        return this.getPerson().getBirthDate();
    }
    public String getAddress() { return this.getPerson().getAddress(); }
    public String getPhoneNumber() { return this.getPerson().getPhoneNumber(); }
    public Sex getSex() { return this.getPerson().getSex(); }
    public int getRole() { return 3; }
    public int getPersonId() { return getPerson().getId(); }
}
