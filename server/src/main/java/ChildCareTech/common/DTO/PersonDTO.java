package ChildCareTech.common.DTO;

import ChildCareTech.common.Sex;

import java.io.Serializable;
import java.rmi.Remote;
import java.time.LocalDate;

public interface PersonDTO extends Serializable, Remote {
    String getFirstName();
    String getLastName();
    String getFiscalCode();
    LocalDate getBirthDate();
    Sex getSex();
    String getAddress();
    String getPhoneNumber();
}
