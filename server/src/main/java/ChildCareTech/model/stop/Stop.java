package ChildCareTech.model.stop;

import ChildCareTech.model.trip.Trip;
import ChildCareTech.model.iEntity;

import javax.persistence.*;

@Entity
@Table(name="stops",
        uniqueConstraints = @UniqueConstraint(columnNames = {"trip_id", "stopNumber"})
)
public class Stop implements iEntity<Stop, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Trip trip;

    @Column(nullable = false)
    private int stopNumber;

    public Stop(){}
    public Stop(Trip trip, int stopNumber){
        this.trip = trip;
        this.stopNumber = stopNumber;
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

    public int getStopNumber() {
        return stopNumber;
    }

    private void setStopNumber(int stopNumber) {
        this.stopNumber = stopNumber;
    }

    @Override
    public Integer getPrimaryKey() {
        return getId();
    }

    @Override
    public void setPrimaryKey(Stop o) {
        setId(o.getPrimaryKey());
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Stop)) return false;
        return this.trip.equals(((Stop) o).trip) &&
                this.stopNumber == ((Stop) o).stopNumber;
    }

    @Override
    public int hashCode() {
        return (Integer.toString(stopNumber) + trip.hashCode()).hashCode();
    }
}
