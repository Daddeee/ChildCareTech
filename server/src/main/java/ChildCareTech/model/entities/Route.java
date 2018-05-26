package ChildCareTech.model.entities;

import ChildCareTech.common.EventStatus;
import ChildCareTech.model.iEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents a row of the Route table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the Route table in the database.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"trip_id", "routeNumber"}))
public class Route implements iEntity<Route, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Trip trip;

    @NotNull(message = "E' necessario specificare il numero della tratta")
    @Column(nullable = false)
    private int routeNumber;

    @NotNull(message = "E' necessario specificare il luogo di partenza")
    @Size(min = 1, message = "E' necessario specificare il luogo di partenza")
    @Column(nullable = false)
    private String departureLocation;


    @NotNull(message = "E' necessario specificare il luogo di arrivo")
    @Size(min = 1, message = "E' necessario specificare il luogo di arrivo")
    @Column(nullable = false)
    private String arrivalLocation;

    private EventStatus status;

    @OneToOne
    private Event departureEvent;
    @OneToOne
    private Event arrivalEvent;

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public Route() {}

    /**
     * Create a Route entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param trip the trip where this route takes place.
     * @param routeNumber the route's number in the trip.
     * @param departureLocation the route's departure location.
     * @param arrivalLocation the route's arrival location.
     * @param status the route's status.
     * @param departureEvent the route's departure event.
     * @param arrivalEvent the route's arrival event.
     */
    public Route(Trip trip, int routeNumber, String departureLocation, String arrivalLocation, EventStatus status, Event departureEvent, Event arrivalEvent) {
        this.trip = trip;
        this.routeNumber = routeNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.status = status;
        this.departureEvent = departureEvent;
        this.arrivalEvent = arrivalEvent;
    }

    /**
     * Create a Route entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param trip the trip where this route takes place.
     * @param routeNumber the route's number in the trip.
     * @param departureLocation the route's departure location.
     * @param arrivalLocation the route's arrival location.
     * @param status the route's status.
     * @param departureEvent the route's departure event.
     * @param arrivalEvent the route's arrival event.
     */
    public Route(int id, Trip trip, int routeNumber, String departureLocation, String arrivalLocation, EventStatus status, Event departureEvent, Event arrivalEvent) {
        this.id = id;
        this.trip = trip;
        this.routeNumber = routeNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.status = status;
        this.departureEvent = departureEvent;
        this.arrivalEvent = arrivalEvent;
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
     * @return the route's trip.
     */
    public Trip getTrip() {
        return trip;
    }

    /**
     * @return the route's number.
     */
    public int getRouteNumber() {
        return routeNumber;
    }

    /**
     * @return the route's departure location.
     */
    public String getDepartureLocation() {
        return departureLocation;
    }

    /**
     * @return the route's arrival location.
     */
    public String getArrivalLocation() {
        return arrivalLocation;
    }

    /**
     * @return the route's status.
     */
    public EventStatus getStatus() {
        return status;
    }

    /**
     * @return the route's departure event.
     */
    public Event getDepartureEvent() {
        return departureEvent;
    }

    /**
     * @return the route's arrival event.
     */
    public Event getArrivalEvent() {
        return arrivalEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        return this.trip.equals(((Route) o).trip) &&
                this.routeNumber == ((Route) o).routeNumber;
    }

    @Override
    public int hashCode() {
        if(trip == null) return super.hashCode();
        return (Integer.toString(routeNumber) + trip.hashCode()).hashCode();
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setTrip(Trip trip) {
        this.trip = trip;
    }

    private void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    private void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    private void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public void setDepartureEvent(Event departureEvent) {
        this.departureEvent = departureEvent;
    }

    public void setArrivalEvent(Event arrivalEvent) {
        this.arrivalEvent = arrivalEvent;
    }
}
