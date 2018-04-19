package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;

import java.util.List;

public class KidDAO extends AbstractGenericDAO<Kid, Integer> {
    public KidDAO() {
        super(Kid.class);
    }

    public List<Kid> getAvailableKids(Trip trip){
        Query<Kid> query = session.createQuery(
                "from Kid " +
                        "where person not in (" +
                            "select t.person " +
                            "from TripPartecipation t " +
                            "where t.trip.depDate <= :arrDate and t.trip.arrDate >= :depDate)"
                , Kid.class)
                .setParameter("arrDate", trip.getArrDate())
                .setParameter("depDate", trip.getDepDate());
        return query.getResultList();
    }

    @Override
    public void initializeLazyRelations(Kid obj) {
        initializeContactsRelation(obj);
    }

    public void initializeContactsRelation(Kid obj) {
        Hibernate.initialize(obj.getContacts());
    }
}