package ChildCareTech.common;

import java.io.Serializable;
import java.time.LocalDate;

public interface PersonDTO extends Serializable {
    String getFirstName();
    String getLastName();
    String getFiscalCode();
    LocalDate getBirthDate();
    Sex getSex();
    String getAddress();
    String getPhoneNumber();

    void setFirstName(String firstName);
    void setLastName(String lastName);
    void setFiscalCode(String fiscalCode);
    void setBirthDate(LocalDate birthDate);
    void setSex(Sex sex);
    void setAddress(String address);
    void setPhoneNumber(String phoneNumber);
}
