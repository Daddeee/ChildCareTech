package ChildCareTech.model.trip;

import ChildCareTech.utils.GenericDAO;

public class TripDAO extends GenericDAO<Trip, Integer> {
    public TripDAO() {
        super(Trip.class);
    }
}