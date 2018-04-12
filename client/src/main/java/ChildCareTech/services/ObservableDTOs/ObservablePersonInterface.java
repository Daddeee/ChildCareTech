package ChildCareTech.services.ObservableDTOs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.format.DateTimeFormatter;

public interface ObservablePersonInterface <typeDTO>{
    StringProperty firstNameProperty();
    StringProperty lastNameProperty();
    StringProperty fiscalCodeProperty();
    StringProperty birthDateProperty();
    StringProperty addressProperty();
    StringProperty roleProperty();
    typeDTO getDTO();
}
