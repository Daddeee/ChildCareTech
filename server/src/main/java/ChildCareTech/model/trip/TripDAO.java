package ChildCareTech.model.trip;

import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class TripDAO extends AbstractGenericDAO<Trip, Integer> {
    public TripDAO() {
        super(Trip.class);
    }

    @Override
    public void initializeLazyRelations(Trip trip){
        initializeRouteRelation(trip);
        initializeTripPartecipationRelation(trip);
    }

    public void initializeRouteRelation(Trip trip){
        Hibernate.initialize(trip.getRoutes());
    }

    public void initializeTripPartecipationRelation(Trip trip){
        Hibernate.initialize(trip.getTripPartecipations());
    }
}