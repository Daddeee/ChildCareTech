package ChildCareTech.services.ObservableDTOs;

import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.PersonDTO;

import java.time.LocalDate;

public class ObservableKid extends KidDTO {
    public ObservableKid(KidDTO kidDTO) {
        super(kidDTO.getPerson(), kidDTO.getFirstTutor(), kidDTO.getSecondTutor(), kidDTO.getPediatrist(), kidDTO.getContacts());
    }
    public String firstNameProperty() {
        return getPerson().getFirstName();
    }
    public String lastNameProperty() {
        return getPerson().getLastName();
    }
    public String fiscalCodeProperty() {
        return getPerson().getFiscalCode();
    }
    public LocalDate birthDateProperty() {
        return getPerson().getBirthDate();
    }
    public String addressProperty() {
        return getPerson().getAddress();
    }
    public String firstTutorFCProperty() {
        return getFirstTutor().getPerson().getFiscalCode();
    }
    public String secondTutorFCProperty() {
        return getSecondTutor().getPerson().getFiscalCode();
    }
    public String pediatristFCProperty() {
        return getPediatrist().getPerson().getFiscalCode();
    }
}
