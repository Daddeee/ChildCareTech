package ChildCareTech.services.ObservableDTOs;

import ChildCareTech.common.DTO.PediatristDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.format.DateTimeFormatter;

public class ObservablePediatrist extends PediatristDTO implements ObservablePersonInterface<PediatristDTO>{
    public ObservablePediatrist(PediatristDTO pediatristDTO) {
        super(pediatristDTO.getId(), pediatristDTO.getPerson(), pediatristDTO.getContacts(), pediatristDTO.getKids());
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
    public StringProperty roleProperty() { return new SimpleStringProperty("pediatra"); }
    public PediatristDTO getDTO() { return new PediatristDTO(getId(), getPerson(), getContacts(), getKids()); }
}
