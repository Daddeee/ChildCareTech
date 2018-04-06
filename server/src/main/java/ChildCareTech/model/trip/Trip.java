package ChildCareTech.model.trip;

import ChildCareTech.model.iEntity;
import ChildCareTech.model.route.Route;
import ChildCareTech.model.trippartecipation.TripPartecipation;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "trips",
        uniqueConstraints = @UniqueConstraint(columnNames = {"meta", "depDate", "arrDate"}))
public class Trip implements iEntity<Trip, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String meta;

    @ColumnDefault("''")
    private String note;

    @Column(nullable = false)
    private LocalDate depDate;

    @Column(nullable = false)
    private LocalDate arrDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "trip")
    private Set<Route> routes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "trip")
    private Set<TripPartecipation> tripPartecipations;

    public Trip() {
    }

    public Trip(String meta, LocalDate depDate, LocalDate arrDate) {
        this.meta = meta;
        this.depDate = depDate;
        this.arrDate = arrDate;
    }

    public Trip(String meta, String note,LocalDate depDate, LocalDate arrDate) {
        this.meta = meta;
        this.note = note;
        this.depDate = depDate;
        this.arrDate = arrDate;
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

    public Set<Route> getRoutes() {
        return routes == null ? Collections.EMPTY_SET : routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    public Set<TripPartecipation> getTripPartecipations() {
        return tripPartecipations == null ? Collections.EMPTY_SET : tripPartecipations;
    }

    public void setTripPartecipations(Set<TripPartecipation> tripPartecipations) {
        this.tripPartecipations = tripPartecipations;
    }

    public LocalDate getArrDate() {
        return arrDate;
    }

    public LocalDate getDepDate() {
        return depDate;
    }

    private void setDepDate(LocalDate depDate) {
        this.depDate = depDate;
    }

    private void setArrDate(LocalDate arrDate) {
        this.arrDate = arrDate;
    }

    @Override
    public Integer getPrimaryKey() {
        return getId();
    }

    @Override
    public void setPrimaryKey(Trip o) {
        setId(o.getPrimaryKey());
    }

    @Override
    public int hashCode() {
        return ("" + meta + depDate + arrDate).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trip)) return false;
        return this.meta.equals(((Trip) o).getMeta()) &&
                this.arrDate.equals(((Trip) o).getArrDate()) &&
                this.depDate.equals(((Trip) o).getDepDate());
    }
}
