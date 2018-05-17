package ChildCareTech.model.entities;

import ChildCareTech.common.Sex;
import ChildCareTech.model.iEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@javax.persistence.Entity
public class Person implements iEntity<Person, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Length(max = 16, min = 16, message = "Il codice fiscale deve avere 16 caratteri.")
    @Column(unique = true)
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "person")
    private Set<Checkpoint> checkpoints = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "person")
    private Set<TripPartecipation> tripPartecipations = new HashSet<>();

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "food_id", nullable = false, updatable = false)},
                inverseJoinColumns = {@JoinColumn(name = "person_id", nullable = false, updatable = false)})
    private Set<Food> allergies = new HashSet<>();

    public Person() {
    }

    public Person(String fiscalCode, String firstName, String lastName, LocalDate birthDate, Sex sex, String address, String phoneNumber) {
        this.fiscalCode = fiscalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Person(int id, String fiscalCode, String firstName, String lastName, LocalDate birthDate, Sex sex, String address, String phoneNumber) {
        this.id = id;
        this.fiscalCode = fiscalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    private void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    private void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    private void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(Set<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public Set<TripPartecipation> getTripPartecipations() {
        return tripPartecipations;
    }

    public void setTripPartecipations(Set<TripPartecipation> tripPartecipations) {
        this.tripPartecipations = tripPartecipations;
    }

    public Set<Food> getAllergies() { return allergies; }

    public void setAllergies(Set<Food> allergies) { this.allergies = allergies; }

    @Override
    public int hashCode() {
        return fiscalCode.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        return this.fiscalCode.equals(((Person) o).fiscalCode);
    }
}
