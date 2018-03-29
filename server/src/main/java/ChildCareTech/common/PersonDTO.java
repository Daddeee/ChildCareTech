package ChildCareTech.common;

import java.time.LocalDate;

public interface PersonDTO {
    public String getFirtsName();
    public String getLastName();
    public String getFiscalCode();
    public LocalDate getBirthDate();
    public Sex getSex();
    public String getAddress();
    public String getPhoneNumber();
}
