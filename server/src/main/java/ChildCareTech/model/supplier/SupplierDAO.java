package ChildCareTech.model.supplier;

import ChildCareTech.utils.AbstractGenericDAO;

public class SupplierDAO extends AbstractGenericDAO<Supplier, Integer> {
    public SupplierDAO() {
        super(Supplier.class);
    }
}