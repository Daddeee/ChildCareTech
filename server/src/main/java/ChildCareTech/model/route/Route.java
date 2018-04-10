package ChildCareTech.model.route;

import ChildCareTech.model.iEntity;
import ChildCareTech.model.trip.Trip;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "routes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"trip_id", "routeNumber"})
)
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

    public Route() {
    }

    public Route(Trip trip, int routeNumber, String departureLocation, String arrivalLocation) {
        this.trip = trip;
        this.routeNumber = routeNumber;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    private void setTrip(Trip trip) {
        this.trip = trip;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    private void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    private void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    private void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    @Override
    public Integer getPrimaryKey() {
        return getId();
    }

    @Override
    public void setPrimaryKey(Route o) {
        setId(o.getPrimaryKey());
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
        return (Integer.toString(routeNumber) + trip.hashCode()).hashCode();
    }
}
