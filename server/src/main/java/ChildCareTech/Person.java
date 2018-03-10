package ChildCareTech;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;


@javax.persistence.Entity
@Table(name="persons")
public class Person implements Entity<String> {
    @Id
    @Column(length=16)
    private String fiscalCode;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Type(type="date")
    private Date birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Sex sex;

    @Column(nullable = false)
    private String address;
    
    private String phoneNumber;

    public Person(){}
    public Person(String fiscalCode, String firstName, String lastName, Date birthDate, Sex sex, String address, String phoneNumber)
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

    public String getFiscalCode()
    {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode)
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

    public Date getBirthDate()
    {
        return birthDate;
    }

    private void setBirthDate(Date birthDate)
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

    @Override
    public boolean equals(Object o) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Person))return false;

        Person p = (Person) o;
        return fiscalCode.equals(p.getFiscalCode()) &&
                firstName.equals(p.getFirstName()) &&
                lastName.equals(p.getLastName()) &&
                sdf.format(birthDate).equals(sdf.format(p.getBirthDate())) &&
                sex == p.getSex() &&
                address.equals(p.getAddress()) &&
                phoneNumber.equals(p.getPhoneNumber());
    }
}
