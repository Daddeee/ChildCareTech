package ChildCareTech.entities;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="trips")
public class Trip implements iEntity<Trip, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String meta;

    @ColumnDefault("''")
    private String note;

    @OneToMany(mappedBy = "trip")
    private Set<Stop> stops;

    @OneToMany(mappedBy = "trip")
    private Set<TripPartecipation> tripPartecipations;

    public Trip(){}
    public Trip(String meta){ this.meta = meta; }
    public Trip(String meta, String note){
        this.meta = meta;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getMeta() {
        return meta;
    }

    private void setMeta(String meta) {
        this.meta = meta;
    }

    public String getNote() {
        return note;
    }

    private void setNote(String note) {
        this.note = note;
    }

    public Set<Stop> getStops() {
        return new HashSet<>(stops);
    }

    public void setStops(Set<Stop> stops) {
        this.stops = stops;
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
    public void setPrimaryKey(Trip o) {
        setId(o.getPrimaryKey());
    }
}
