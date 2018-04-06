package ChildCareTech.services.ObservableDTOs;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ObservableKid extends KidDTO {
    public ObservableKid(KidDTO kidDTO) {
        super(kidDTO.getPerson(), kidDTO.getFirstTutor(), kidDTO.getSecondTutor(), kidDTO.getPediatrist(), kidDTO.getContacts());
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
    public StringProperty firstTutorFCProperty() {
        return new SimpleStringProperty(getFirstTutor().getPerson().getFiscalCode());
    }
    public StringProperty secondTutorFCProperty() {
        return new SimpleStringProperty(getSecondTutor().getPerson().getFiscalCode());
    }
    public StringProperty pediatristFCProperty() {
        return new SimpleStringProperty(getPediatrist().getPerson().getFiscalCode());
    }
}
