package ChildCareTech.services.ObservableDTOs;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ObservableAdult extends AdultDTO implements ObservablePersonInterface<AdultDTO>{
    public ObservableAdult(AdultDTO adultDTO) {
        super(adultDTO.getId(), adultDTO.getPerson(), adultDTO.getContacts());
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
    public StringProperty roleProperty() { return new SimpleStringProperty("-"); }
    public AdultDTO getDTO() { return new AdultDTO(getId(), getPerson(), getContacts()); }

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
    public int getRole() { return 0; }
    public int getPersonId() { return getPerson().getId(); }
}
