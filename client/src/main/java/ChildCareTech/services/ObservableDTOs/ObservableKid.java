package ChildCareTech.services.ObservableDTOs;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * This class provides a wrapper for the {@link KidDTO KidDTO} class.
 * The purpose is to simplify KidDTO instances' display in tables.
 */
public class ObservableKid extends KidDTO {
    private static final StringProperty EMPTY_FISCAL_CODE = new SimpleStringProperty("-");

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
        if(getFirstTutor() == null) return EMPTY_FISCAL_CODE;
        return new SimpleStringProperty(getFirstTutor().getPerson().getFiscalCode());
    }
    public StringProperty secondTutorFCProperty() {
        if(getSecondTutor() == null) return EMPTY_FISCAL_CODE;
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
        if(getFirstTutor() == null)
            return "-";
        return getFirstTutor().getPerson().getFiscalCode();
    }
    public String getSecondTutorFC() {
        if(getSecondTutor() == null)
            return "-";
        return getSecondTutor().getPerson().getFiscalCode();
    }
    public String getPediatristCF() {
        return  getPediatrist().getPerson().getFiscalCode();
    }
    public int getPersonId() { return getPerson().getId(); }
}
