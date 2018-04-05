package ChildCareTech.model.pediatrist;

import ChildCareTech.utils.AbstractGenericDAO;

public class PediatristDAO extends AbstractGenericDAO<Pediatrist, Integer> {
    public PediatristDAO() {
        super(Pediatrist.class);
    }
}