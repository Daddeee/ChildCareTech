package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a row of the Bus table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Bus table in the database.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"licensePlate"}))
public class Bus implements iEntity<Bus, Integer> {
    private static final String LICENSE_PLATE_REGEX = "[a-zA-Z]{2}\\d{3}[a-zA-Z]{2}$";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Pattern(regexp = LICENSE_PLATE_REGEX, message = "Formato della targa non valido")
    @Column(nullable = false, unique = true, length = 30)
    private String licensePlate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "bus")
    private Set<TripPartecipation> tripPartecipations;

    @Range(min=0, message = "La capienza di un autobus non può essere negativa")
    @Column(nullable = false)
    private int capacity;

    @ManyToMany(mappedBy = "buses")
    private Set<Trip> trips;

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Bus() {
    }

    /**
     * Create a Bus entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param licensePlate the license plate of the bus.
     * @param capacity the number of sits of the bus.
     */
    public Bus(String licensePlate, int capacity) {
        this.licensePlate = licensePlate;
        this.capacity = capacity;
    }

    /**
     * Create a Bus entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param licensePlate the license plate of the bus.
     * @param capacity the number of sits of the bus.
     */
    public Bus(int id, String licensePlate, int capacity) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.capacity = capacity;
    }

    @Override
    public Integer getPrimaryKey() {
        return getId();
    }

    /**
     * @return the entity's id.
     */
    public int getId() {
        return id;
    }

    /**
     * @return the license plate of the bus.
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * @return the capacity of the bus.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @return a Set containing all the {@link TripPartecipation TripPartecipation(s)} associated to this Bus.
     */
    public Set<TripPartecipation> getTripPartecipations() {
        return tripPartecipations == null ? Collections.EMPTY_SET : tripPartecipations;
    }

    /**
     * @param tripPartecipations a Set containing the {@link TripPartecipation TripPartecipation(s)} associated to this Bus.
     */
    public void setTripPartecipations(Set<TripPartecipation> tripPartecipations) {
        this.tripPartecipations = tripPartecipations;
    }

    /**
     * @return a Set containing all the {@link Trip Trip(s)} associated to this Bus.
     */
    public Set<Trip> getTrips() {
        return trips;
    }

    /**
     * @param trips a Set containing the {@link Trip Trip(s)} associated to this Bus.
     */
    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public int hashCode() {
        return licensePlate.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Bus)) return false;
        return this.licensePlate.equals(((Bus) other).licensePlate);
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
