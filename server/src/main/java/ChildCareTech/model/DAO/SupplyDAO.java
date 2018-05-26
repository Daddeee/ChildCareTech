package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Supply;
import ChildCareTech.model.AbstractGenericDAO;

public class SupplyDAO extends AbstractGenericDAO<Supply, Integer> {
    public SupplyDAO() {
        super(Supply.class);
    }

    @Override
    public void initializeLazyRelations(Supply obj) {

    }
}