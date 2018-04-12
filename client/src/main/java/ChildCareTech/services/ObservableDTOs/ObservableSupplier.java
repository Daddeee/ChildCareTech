package ChildCareTech.services.ObservableDTOs;

import ChildCareTech.common.DTO.SupplierDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.format.DateTimeFormatter;

public class ObservableSupplier extends SupplierDTO implements ObservablePersonInterface<SupplierDTO>{
    public ObservableSupplier(SupplierDTO supplierDTO) {
        super(supplierDTO.getId(), supplierDTO.getPerson(), supplierDTO.getContacts(), supplierDTO.getSupplies());
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
    public StringProperty roleProperty() { return new SimpleStringProperty("fornitore"); }
    public SupplierDTO getDTO() { return new SupplierDTO(getId(), getPerson(), getContacts(), getSupplies()); }
}
