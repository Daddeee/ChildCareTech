package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Bus;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

public class BusDAO extends AbstractGenericDAO<Bus, Integer> {
    public BusDAO() {
        super(Bus.class);
    }

    public List<Bus> getAvailableBuses(Trip trip){
        Query<Bus> query = session.createQuery(
                "from Bus " +
                   "where id not in (select b.id " +
                        "from Bus b join b.trips t " +
                        "where (t.depDate <= :arrDate and t.arrDate >= :depDate) " +
                                "or t.id = :id)"
                , Bus.class)
                .setParameter("id", trip.getId())
                .setParameter("arrDate", trip.getArrDate())
                .setParameter("depDate", trip.getDepDate());
        return query.getResultList();
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