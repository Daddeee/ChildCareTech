package ChildCareTech.model.trip;

import ChildCareTech.utils.AbstractGenericDAO;

public class TripDAO extends AbstractGenericDAO<Trip, Integer> {
    public TripDAO() {
        super(Trip.class);
    }
}