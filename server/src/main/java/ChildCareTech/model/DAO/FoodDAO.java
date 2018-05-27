package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Dish;
import ChildCareTech.model.entities.Food;
import ChildCareTech.model.entities.Person;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;

import java.util.List;

/**
 * A Data Access Object that operates with Food entities.
 */
public class FoodDAO extends AbstractGenericDAO<Food, Integer> {
    public FoodDAO() {
        super(Food.class);
    }

    /**
     * Get all foods to which a person is not yet allergic.
     *
     * @param person the person for which foods are searched.
     * @return a List containing all foods entities except the ones to which the person is already allergic.
     */
    public List<Food> getAvailableFoods(Person person){
        Query<Food> query = session.createQuery(
                "from Food " +
                        "where id not in ( select f.id " +
                        "from Person p join p.allergies f " +
                        "where p.fiscalCode = :fiscalCode )"
        , Food.class).setParameter("fiscalCode", person.getFiscalCode());

        return query.getResultList();
    }

    /**
     * Get all foods that are not yet used as ingredients for the provided dish.
     *
     * @param dish the dish for which foods are searched.
     * @return a List containing all available food entities.
     */
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