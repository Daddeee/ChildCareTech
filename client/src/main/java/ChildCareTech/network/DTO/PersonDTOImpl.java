package ChildCareTech.network.DTO;

import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;
import ChildCareTech.services.SessionService;

import java.time.LocalDate;

public class PersonDTOImpl implements PersonDTO {
    private String firstName;
    private String lastName;
    private String fiscalCode;
    private LocalDate birthDate;
    private Sex sex;
    private String address;
    private String phoneNumber;

    public PersonDTOImpl(String firstName, String lastName, String fiscalCode, LocalDate birthDate, Sex sex, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fiscalCode = fiscalCode;
        this.birthDate = birthDate;
        this.sex = sex;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getFiscalCode() {
        return fiscalCode;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public Sex getSex() {
        return sex;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
