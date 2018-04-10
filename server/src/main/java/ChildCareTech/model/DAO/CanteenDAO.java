package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Canteen;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class CanteenDAO extends AbstractGenericDAO<Canteen, Integer> {
    public CanteenDAO() {
        super(Canteen.class);
    }

    @Override
    public void initializeLazyRelations(Canteen obj) {
        initializeMealRelation(obj);
    }

    public void initializeMealRelation(Canteen obj) {
        Hibernate.initialize(obj.getMeals());
    }
}