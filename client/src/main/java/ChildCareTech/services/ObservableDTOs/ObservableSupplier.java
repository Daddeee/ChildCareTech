package ChildCareTech.services.ObservableDTOs;

import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.Sex;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * This class provides a wrapper for the {@link SupplierDTO SupplierDTO} class.
 * The purpose is to simplify SupplierDTO instances' display in tables.
 */
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
    public int getRole() { return 2; }
    public int getPersonId() { return getPerson().getId(); }
}
