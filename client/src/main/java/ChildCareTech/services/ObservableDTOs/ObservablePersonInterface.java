package ChildCareTech.services.ObservableDTOs;

import ChildCareTech.common.Sex;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This interface should be implemented by classes that extend {@link ChildCareTech.common.DTO.PersonDTO PersonDTO} class
 * and need to be displayed in tables.
 * @param <typeDTO>
 */
public interface ObservablePersonInterface <typeDTO>{
    StringProperty firstNameProperty();
    StringProperty lastNameProperty();
    StringProperty fiscalCodeProperty();
    StringProperty birthDateProperty();
    StringProperty addressProperty();
    StringProperty roleProperty();

    String getFirstName();
    String getLastName();
    String getFiscalCode();
    LocalDate getBirthDate();
    String getAddress();
    String getPhoneNumber();
    Sex getSex();
    int getId();
    int getPersonId();
    int getRole();

    typeDTO getDTO();
}
