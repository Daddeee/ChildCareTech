package ChildCareTech.model.supply;

import ChildCareTech.utils.AbstractGenericDAO;

public class SupplyDAO extends AbstractGenericDAO<Supply, Integer> {
    public SupplyDAO() {
        super(Supply.class);
    }
}