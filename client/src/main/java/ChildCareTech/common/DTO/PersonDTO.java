package ChildCareTech.common.DTO;

import ChildCareTech.common.Sex;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

/**
 * This class provides a Data Transfer Object encapsulation for Person entity.
 */
public class PersonDTO implements Serializable {
    private int id;
    private String fiscalCode;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Sex sex;
    private String address;
    private String phoneNumber;

    private Set<FoodDTO> allergies;

    public PersonDTO(int id, String fiscalCode, String firstName, String lastName, LocalDate birthDate, Sex sex, String address, String phoneNumber, Set<FoodDTO> allergies) {
        this.id = id;
        this.fiscalCode = fiscalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.allergies = allergies == null ? Collections.EMPTY_SET : allergies;
    }

    public int getId() { return id; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<FoodDTO> getAllergies() { return allergies; }

    public void setAllergies(Set<FoodDTO> allergies) { this.allergies = allergies; }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof PersonDTO)) return false;
        return ((PersonDTO) o).fiscalCode.equals(this.fiscalCode);
    }
}
