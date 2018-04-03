package ChildCareTech.model.bus;

import ChildCareTech.utils.GenericDAO;

public class BusDAO extends GenericDAO<Bus, Integer> {
    public BusDAO() {
        super(Bus.class);
    }
}