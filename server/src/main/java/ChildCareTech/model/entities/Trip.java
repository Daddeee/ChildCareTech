package ChildCareTech.model.entities;

import ChildCareTech.common.EventStatus;
import ChildCareTech.model.iEntity;
import ChildCareTech.model.validators.ValidRoutes;
import ChildCareTech.model.validators.ValidTripDates;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"meta", "depDate", "arrDate"}))
@ValidTripDates(message = "La data di arrivo non può precedere la data di partenza.")
@ValidRoutes(message ="Le tratte inserite non sono in sequenza.")
public class Trip implements iEntity<Trip, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "E' necessario specificare la meta.")
    @Size(min = 1, message = "E' necessario specificare la meta.")
    @Column(nullable = false)
    private String meta;

    @ColumnDefault("''")
    private String note;

    @NotNull(message = "E' necessario specificare la data di partenza.")
    @Column(nullable = false)
    private LocalDate depDate;

    @NotNull(message = "E' necessario specificare la data di arrivo.")
    @Column(nullable = false)
    private LocalDate arrDate;

    private EventStatus status;

    @Valid
    @Size(min = 2, message = "Inserire almeno 2 tratte (andata e ritorno)")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "trip")
    private Set<Route> routes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "trip")
    private Set<TripPartecipation> tripPartecipations;

    @ManyToMany
    @JoinTable(joinColumns = { @JoinColumn(name = "trip_id") }, inverseJoinColumns = { @JoinColumn(name = "bus_id") })
    private Set<Bus> buses;

    public Trip() {
    }

    public Trip(String meta, LocalDate depDate, LocalDate arrDate, EventStatus status) {
        this.meta = meta;
        this.depDate = depDate;
        this.arrDate = arrDate;
        this.status = status;
    }

    public Trip(String meta, String note,LocalDate depDate, LocalDate arrDate, EventStatus status) {
        this.meta = meta;
        this.note = note;
        this.depDate = depDate;
        this.arrDate = arrDate;
        this.status = status;
    }

    public Trip(int id, String meta, String note,LocalDate depDate, LocalDate arrDate, EventStatus status) {
        this.id = id;
        this.meta = meta;
        this.note = note;
        this.depDate = depDate;
        this.arrDate = arrDate;
        this.status = status;
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
        return tripPartecipations;
    }

    public void setTripPartecipations(Set<TripPartecipation> tripPartecipations) {
        this.tripPartecipations = tripPartecipations;
    }

    public Set<Bus> getBuses() {
        return buses;
    }

    public void setBuses(Set<Bus> buses) {
        this.buses = buses;
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

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    @Override
    public Integer getPrimaryKey() {
        return getId();
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
