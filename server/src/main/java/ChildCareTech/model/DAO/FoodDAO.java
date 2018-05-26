package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;

import java.util.List;

public class FoodDAO extends AbstractGenericDAO<Food, Integer> {
    public FoodDAO() {
        super(Food.class);
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

    public List<Food> getAvailableFoods(Dish dish){
        Query<Food> query = session.createQuery(
                "from Food " +
                        "where id not in ( " +
                        "select f.id " +
                        "from Dish d join d.foods f " +
                        "where d.id = :id)"
                , Food.class).setParameter("id", dish.getId());

        return query.getResultList();
    }

    @Override
    public void initializeLazyRelations(Food obj) {
        initializeDishRelation(obj);
        initializeSupplyRelation(obj);
    }

    private void initializeDishRelation(Food obj) {}

    private void initializeSupplyRelation(Food obj){
        Hibernate.initialize(obj.getSupplies());
    }
}