package ChildCareTech.services.ObservableDTOs;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ObservableKid extends KidDTO {
    public ObservableKid(KidDTO kidDTO) {
        super( kidDTO.getId(), kidDTO.getPerson(), kidDTO.getFirstTutor(), kidDTO.getSecondTutor(), kidDTO.getPediatrist(), kidDTO.getContacts());
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
    public StringProperty sexProperty() { return new SimpleStringProperty(getPerson().getSex().toString()); }
    public StringProperty birthDateProperty() {
        DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return new SimpleStringProperty(getPerson().getBirthDate().format(myDateFormatter));
    }
    public StringProperty addressProperty() {
        return new SimpleStringProperty(getPerson().getAddress());
    }
    public StringProperty firstTutorFCProperty() {
        return new SimpleStringProperty(getFirstTutor().getPerson().getFiscalCode());
    }
    public StringProperty secondTutorFCProperty() {
        return new SimpleStringProperty(getSecondTutor().getPerson().getFiscalCode());
    }
    public StringProperty pediatristFCProperty() {
        return new SimpleStringProperty(getPediatrist().getPerson().getFiscalCode());
    }
    public KidDTO getDTO() { return new KidDTO(getId(), getPerson(), getFirstTutor(), getSecondTutor(), getPediatrist(), getContacts()); }

    public String getFirstName() {
        return getPerson().getFirstName();
    }
    public String getLastName() {
        return getPerson().getLastName();
    }
    public String getFiscalCode() {
        return getPerson().getFiscalCode();
    }
    public String getAddress() {
        return getPerson().getAddress();
    }
    public Sex getSex() {
        return getPerson().getSex();
    }
    public LocalDate getBirthDate() {
        return getPerson().getBirthDate();
    }
    public String getFirstTutorFC() {
        return getFirstTutor().getPerson().getFiscalCode();
    }
    public String getSecondTutorFC() {
        return getSecondTutor().getPerson().getFiscalCode();
    }
    public String getPediatristCF() {
        return  getPediatrist().getPerson().getFiscalCode();
    }
}
