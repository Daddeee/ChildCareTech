package ChildCareTech.model.bus;

import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class BusDAO extends AbstractGenericDAO<Bus, Integer> {
    public BusDAO() {
        super(Bus.class);
    }

    @Override
    public void initializeLazyRelations(Bus obj) {
        initializeTripPartecipationRelation(obj);
    }

    public void initializeTripPartecipationRelation(Bus obj) {
        Hibernate.initialize(obj.getTripPartecipations());
    }
}