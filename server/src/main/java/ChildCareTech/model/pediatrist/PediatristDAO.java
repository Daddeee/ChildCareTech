package ChildCareTech.model.pediatrist;

import ChildCareTech.model.adult.AdultDAO;
import ChildCareTech.utils.AbstractGenericDAO;
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

    public void initializeKidRelation(Pediatrist obj){
        Hibernate.initialize(obj.getKids());
    }
}