package ChildCareTech.model.bus;

import ChildCareTech.utils.AbstractGenericDAO;

public class BusDAO extends AbstractGenericDAO<Bus, Integer> {
    public BusDAO() {
        super(Bus.class);
    }
}