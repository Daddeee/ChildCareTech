package ChildCareTech.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="buses")
public class Bus implements iEntity<Bus, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    @OneToMany
    private Set<TripPartecipation> tripPartecipations;

    public Bus(){}
    public Bus(String licensePlate) { this.licensePlate = licensePlate; }

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
        return new HashSet<>(tripPartecipations);
    }

    public void setTripPartecipations(Set<TripPartecipation> tripPartecipations) {
        this.tripPartecipations = tripPartecipations;
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
        if(this == other) return true;
        if(!(other instanceof Bus)) return false;
        return this.licensePlate.equals(((Bus) other).licensePlate);
    }
}
