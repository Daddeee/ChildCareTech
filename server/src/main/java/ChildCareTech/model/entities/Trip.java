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

/**
 * Represents a row of the Trip table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Trip table in the database.
 */
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
    @Column(nullable = false, length = 40)
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

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Trip() {}

    /**
     * Create a Trip entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param meta the trip's destination.
     * @param depDate the trip's departure date.
     * @param arrDate the trip's arrival date.
     * @param status the trip's status.
     */
    public Trip(String meta, LocalDate depDate, LocalDate arrDate, EventStatus status) {
        this.meta = meta;
        this.depDate = depDate;
        this.arrDate = arrDate;
        this.status = status;
    }

    /**
     * Create a Trip entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param meta the trip's destination.
     * @param note eventual note on the trip.
     * @param depDate the trip's departure date.
     * @param arrDate the trip's arrival date.
     * @param status the trip's status.
     */
    public Trip(String meta, String note,LocalDate depDate, LocalDate arrDate, EventStatus status) {
        this.meta = meta;
        this.note = note;
        this.depDate = depDate;
        this.arrDate = arrDate;
        this.status = status;
    }

    /**
     * Create a Trip entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param meta the trip's destination.
     * @param note eventual note on the trip.
     * @param depDate the trip's departure date.
     * @param arrDate the trip's arrival date.
     * @param status the trip's status.
     */
    public Trip(int id, String meta, String note,LocalDate depDate, LocalDate arrDate, EventStatus status) {
        this.id = id;
        this.meta = meta;
        this.note = note;
        this.depDate = depDate;
        this.arrDate = arrDate;
        this.status = status;
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
     * @return the trip's destination.
     */
    public String getMeta() {
        return meta;
    }

    /**
     * @return the trip's note.
     */
    public String getNote() {
        return note;
    }

    /**
     * @return the trip's departure date.
     */
    public LocalDate getDepDate() {
        return depDate;
    }

    /**
     * @return the trip's arrival date.
     */
    public LocalDate getArrDate() {
        return arrDate;
    }

    /**
     * @return the trip's departure date.
     */
    public EventStatus getStatus() {
        return status;
    }

    /**
     * @param status the trip's new status.
     */
    public void setStatus(EventStatus status) {
        this.status = status;
    }

    /**
     * @return a Set containing all the {@link Route Route(s)} associated to this Trip.
     */
    public Set<Route> getRoutes() {
        return routes == null ? Collections.EMPTY_SET : routes;
    }

    /**
     * @param routes a Set containing the {@link Route Route(s)} associated to this Trip.
     */
    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    /**
     * @return a Set containing all the {@link TripPartecipation TripPartecipation(s)} associated to this Trip.
     */
    public Set<TripPartecipation> getTripPartecipations() {
        return tripPartecipations;
    }

    /**
     * @param tripPartecipations a Set containing the {@link TripPartecipation TripPartecipation(s)} associated to this Trip.
     */
    public void setTripPartecipations(Set<TripPartecipation> tripPartecipations) {
        this.tripPartecipations = tripPartecipations;
    }

    /**
     * @return a Set containing all the {@link Bus Bus(es)} associated to this Trip.
     */
    public Set<Bus> getBuses() {
        return buses;
    }

    /**
     * @param buses a Set containing the {@link Bus Bus(es)} associated to this Trip.
     */
    public void setBuses(Set<Bus> buses) {
        this.buses = buses;
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

    private void setId(int id) {
        this.id = id;
    }

    private void setMeta(String meta) {
        this.meta = meta;
    }

    private void setNote(String note) {
        this.note = note;
    }

    private void setDepDate(LocalDate depDate) {
        this.depDate = depDate;
    }

    private void setArrDate(LocalDate arrDate) {
        this.arrDate = arrDate;
    }
}
