package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Trip;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class TripDAO extends AbstractGenericDAO<Trip, Integer> {
    public TripDAO() {
        super(Trip.class);
    }

    @Override
    public void initializeLazyRelations(Trip trip){
        initializeRouteRelation(trip);
        initializeTripPartecipationRelation(trip);
        initializeBusRelation(trip);
    }

    private void initializeRouteRelation(Trip trip){
        Hibernate.initialize(trip.getRoutes());
    }

    private void initializeTripPartecipationRelation(Trip trip){
        Hibernate.initialize(trip.getTripPartecipations());
    }

    private void initializeBusRelation(Trip trip) { Hibernate.initialize(trip.getBuses()); }
}