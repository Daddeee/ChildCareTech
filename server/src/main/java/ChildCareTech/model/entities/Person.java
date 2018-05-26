package ChildCareTech.model.entities;

import ChildCareTech.common.Sex;
import ChildCareTech.model.iEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a row of the Person table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Person table in the database.
 */
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
    @JoinTable(joinColumns = {@JoinColumn(name = "person_id", nullable = false, updatable = false)},
                inverseJoinColumns = {@JoinColumn(name = "food_id", nullable = false, updatable = false)})
    private Set<Food> allergies = new HashSet<>();

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Person() {}

    /**
     * Create a Person entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param fiscalCode the Person's fiscalcode.
     * @param firstName the Person's first name.
     * @param lastName the Person's last name.
     * @param birthDate the Person's birth date.
     * @param sex the Person's gender.
     * @param address the Person's address.
     * @param phoneNumber the Person's phone number.
     */
    public Person(String fiscalCode, String firstName, String lastName, LocalDate birthDate, Sex sex, String address, String phoneNumber) {
        this.fiscalCode = fiscalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Create a Person entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param fiscalCode the Person's fiscalcode.
     * @param firstName the Person's first name.
     * @param lastName the Person's last name.
     * @param birthDate the Person's birth date.
     * @param sex the Person's gender.
     * @param address the Person's address.
     * @param phoneNumber the Person's phone number.
     */
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

    /**
     * @return the entity's id.
     */
    public int getId() {
        return id;
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    /**
     * @return the person's fiscalcode.
     */
    public String getFiscalCode() {
        return fiscalCode;
    }

    /**
     * @return the person's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @return the person's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the person's birth date.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * @return the person's gender.
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * @return the person's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the person's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return a Set containing all the {@link Checkpoint Checkpoint(s)} associated to this Person.
     */
    public Set<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    /**
     * @param checkpoints a Set containing the {@link Checkpoint Checkpoint(s)} associated to this Person.
     */
    public void setCheckpoints(Set<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    /**
     * @return a Set containing all the {@link TripPartecipation TripPartecipation(s)} associated to this Person.
     */
    public Set<TripPartecipation> getTripPartecipations() {
        return tripPartecipations;
    }

    /**
     * @param tripPartecipations a Set containing the {@link TripPartecipation TripPartecipation(s)} associated to this Person.
     */
    public void setTripPartecipations(Set<TripPartecipation> tripPartecipations) {
        this.tripPartecipations = tripPartecipations;
    }

    /**
     * @return a Set containing all the {@link Food Food(s)} that this Person is allergic to.
     */
    public Set<Food> getAllergies() { return allergies; }

    /**
     * @param allergies a Set containing the {@link Food Food(s)} that this Person is allergic to.
     */
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

    private void setId(int id) {
        this.id = id;
    }

    private void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    private void setSex(Sex sex) {
        this.sex = sex;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
