package ChildCareTech.model.supply;

import ChildCareTech.utils.GenericDAO;

public class SupplyDAO extends GenericDAO<Supply, Integer> {
    public SupplyDAO() {
        super(Supply.class);
    }
}