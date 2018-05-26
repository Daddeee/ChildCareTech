package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Pediatrist;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class PediatristDAO extends AbstractGenericDAO<Pediatrist, Integer> {
    public PediatristDAO() {
        super(Pediatrist.class);
    }

    @Override
    public void initializeLazyRelations(Pediatrist obj) {
        AdultDAO parentEntityDAO = new AdultDAO();
        parentEntityDAO.initializeLazyRelations(obj);
        initializeKidRelation(obj);
    }

    private void initializeKidRelation(Pediatrist obj){
        Hibernate.initialize(obj.getKids());
    }
}