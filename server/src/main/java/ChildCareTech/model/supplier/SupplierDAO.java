package ChildCareTech.model.supplier;

import ChildCareTech.model.adult.AdultDAO;
import ChildCareTech.utils.AbstractGenericDAO;
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

    public void initializeSupplyRelation(Supplier obj) {
        Hibernate.initialize(obj.getSupplies());
    }
}