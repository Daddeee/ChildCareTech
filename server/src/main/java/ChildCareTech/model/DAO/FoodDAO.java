package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;
import ChildCareTech.utils.AbstractGenericDAO;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;

import java.util.List;

public class FoodDAO extends AbstractGenericDAO<Food, Integer> {
    public FoodDAO() {
        super(Food.class);
    }

    @Override
    public void initializeLazyRelations(Food obj) {
        initializeDishRelation(obj);
        initializeSupplyRelation(obj);
    }

    public List<Food> getAvailableFoods(Person person){
        Query<Food> query = session.createQuery(
                "from Food " +
                        "where id not in ( select f.id " +
                        "from Person p join p.allergies f " +
                        "where p.fiscalCode = :fiscalCode )"
        , Food.class).setParameter("fiscalCode", person.getFiscalCode());

        return query.getResultList();
    }

    public void initializeDishRelation(Food obj) {}

    public void initializeSupplyRelation(Food obj){
        Hibernate.initialize(obj.getSupplies());
    }
}