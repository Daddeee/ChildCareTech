package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Canteen;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;

import java.util.List;

public class CanteenDAO extends AbstractGenericDAO<Canteen, Integer> {
    public CanteenDAO() {
        super(Canteen.class);
    }

    public List<String> getAllNames() {
        Query<String> query = session.createQuery("select name from Canteen", String.class);
        return query.list();
    }

    @Override
    public void initializeLazyRelations(Canteen obj) {
        initializeMealRelation(obj);
    }

    public void initializeMealRelation(Canteen obj) {
        Hibernate.initialize(obj.getMeals());
    }
}