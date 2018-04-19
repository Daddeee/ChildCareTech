package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Collections;
import java.util.Set;

@Entity
public class Bus implements iEntity<Bus, Integer> {
    private static final String LICENSE_PLATE_REGEX = "[a-zA-Z]{2}\\d{3}[a-zA-Z]{2}$";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Pattern(regexp = LICENSE_PLATE_REGEX, message = "Formato della targa non valido")
    @Column(nullable = false, unique = true)
    private String licensePlate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "bus")
    private Set<TripPartecipation> tripPartecipations;

    @Range(min=0, message = "La capienza di un autobus non può essere negativa")
    @Column(nullable = false)
    private int capacity;

    @ManyToMany
    private Set<Trip> trips;

    public Bus() {
    }

    public Bus(String licensePlate, int capacity) {
        this.licensePlate = licensePlate;
        this.capacity = capacity;
    }

    public Bus(int id, String licensePlate, int capacity) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    private void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Set<TripPartecipation> getTripPartecipations() {
        return tripPartecipations == null ? Collections.EMPTY_SET : tripPartecipations;
    }

    public void setTripPartecipations(Set<TripPartecipation> tripPartecipations) {
        this.tripPartecipations = tripPartecipations;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

    public int getCapacity() {
        return capacity;
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Integer getPrimaryKey() {
        return getId();
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
}
