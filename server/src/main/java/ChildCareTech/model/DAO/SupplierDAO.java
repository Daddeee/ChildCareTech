package ChildCareTech.model.DAO;

import ChildCareTech.model.DAO.AdultDAO;
import ChildCareTech.model.entities.Supplier;
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