package ChildCareTech.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;


@javax.persistence.Entity
@Table(name="persons")
public class Person implements iEntity<Person, String> {
    @Id
    @Column(length=16)
    private String fiscalCode;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Sex sex;

    @Column(nullable = false)
    private String address;
    
    private String phoneNumber;

    public Person(){}
    public Person(String fiscalCode, String firstName, String lastName, LocalDate birthDate, Sex sex, String address, String phoneNumber)
    {
        this.fiscalCode = fiscalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public enum Sex
    {
        MALE, FEMALE
    }

    @Override
    public String getPrimaryKey() {
        return fiscalCode;
    }
    @Override
    public void setPrimaryKey(Person a) { setFiscalCode(a.getFiscalCode()); }

    public String getFiscalCode()
    {
        return fiscalCode;
    }

    private void setFiscalCode(String fiscalCode)
    {
        this.fiscalCode = fiscalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    private void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    private void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    public Sex getSex()
    {
        return sex;
    }

    private void setSex(Sex sex)
    {
        this.sex = sex;
    }

    public String getAddress()
    {
        return address;
    }

    private void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    private void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}
