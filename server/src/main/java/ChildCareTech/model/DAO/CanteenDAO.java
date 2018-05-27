package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Canteen;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;

import java.util.List;

/**
 * A Data Access Object that operates with Canteen entities.
 */
public class CanteenDAO extends AbstractGenericDAO<Canteen, Integer> {
    public CanteenDAO() {
        super(Canteen.class);
    }

    /**
     * @return a List containing all the canteen names saved in the database.
     */
    public List<String> getAllNames() {
        Query<String> query = session.createQuery("select name from Canteen", String.class);
        return query.list();
    }

    @Override
    public void initializeLazyRelations(Canteen obj) {
        initializeMealRelation(obj);
    }

    private void initializeMealRelation(Canteen obj) {
        Hibernate.initialize(obj.getMeals());
    }
}