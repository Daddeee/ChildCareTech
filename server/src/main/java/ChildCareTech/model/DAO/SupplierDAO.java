package ChildCareTech.model.DAO;

import ChildCareTech.model.entities.Supplier;
import ChildCareTech.model.AbstractGenericDAO;
import org.hibernate.Hibernate;

public class SupplierDAO extends AbstractGenericDAO<Supplier, Integer> {
    public SupplierDAO() {
        super(Supplier.class);
    }

    @Override
    public void initializeLazyRelations(Supplier obj) {
        AdultDAO parentEntityDAO = new AdultDAO();
        parentEntityDAO.initializeLazyRelations(obj);

        initializeSupplyRelation(obj);
    }

    private void initializeSupplyRelation(Supplier obj) {
        Hibernate.initialize(obj.getSupplies());
    }
}