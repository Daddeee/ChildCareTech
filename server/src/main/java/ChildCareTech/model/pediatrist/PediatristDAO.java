package ChildCareTech.model.pediatrist;

import ChildCareTech.utils.GenericDAO;

public class PediatristDAO extends GenericDAO<Pediatrist, Integer> {
    public PediatristDAO() {
        super(Pediatrist.class);
    }
}