package ChildCareTech.model.bus;

import ChildCareTech.model.iEntity;
import ChildCareTech.model.trippartecipation.TripPartecipation;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "buses")
public class Bus implements iEntity<Bus, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "bus")
    private Set<TripPartecipation> tripPartecipations;

    @Column(nullable = false)
    private int capacity;

    public Bus() {
    }

    public Bus(String licensePlate) {
        this.licensePlate = licensePlate;
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
    public void setPrimaryKey(Bus o) {
        setId(o.getPrimaryKey());
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
