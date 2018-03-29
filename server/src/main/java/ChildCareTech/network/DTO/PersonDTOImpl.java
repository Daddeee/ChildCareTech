package ChildCareTech.network.DTO;

import ChildCareTech.common.PersonDTO;
import ChildCareTech.common.Sex;

import java.rmi.RemoteException;
import java.time.LocalDate;

public class PersonDTOImpl implements PersonDTO {
    private String firstName;
    private String lastName;
    private String fiscalCode;
    private LocalDate birthDate;
    private Sex sex;
    private String address;
    private String phoneNumber;

    public PersonDTOImpl() {}

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFiscalCode() {
        return fiscalCode;
    }

    @Override
    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public Sex getSex() {
        return sex;
    }

    @Override
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
