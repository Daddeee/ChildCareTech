package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Bus;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class BusDAO extends AbstractGenericDAO<Bus, Integer> {
    public BusDAO() {
        super(Bus.class);
    }

    @Override
    public void initializeLazyRelations(Bus obj) {
        initializeTripPartecipationRelation(obj);
        initializeTripRelation(obj);
    }

    public void initializeTripPartecipationRelation(Bus obj) {
        Hibernate.initialize(obj.getTripPartecipations());
    }

    public void initializeTripRelation(Bus obj) {
        Hibernate.initialize(obj.getTrips());
    }
}