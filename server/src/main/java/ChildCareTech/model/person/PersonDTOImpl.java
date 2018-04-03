package ChildCareTech.model.person;

import ChildCareTech.common.DTO.PersonDTO;
import ChildCareTech.common.Sex;

import java.time.LocalDate;

public class PersonDTOImpl implements PersonDTO {
    private String firstName;
    private String lastName;
    private String fiscalCode;
    private LocalDate birthDate;
    private Sex sex;
    private String address;
    private String phoneNumber;

    public PersonDTOImpl(Person person) {
        firstName = person.getFirstName();
        lastName = person.getLastName();
        fiscalCode = person.getFiscalCode();
        birthDate = person.getBirthDate();
        sex = person.getSex();
        address = person.getAddress();
        phoneNumber = person.getPhoneNumber();
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
