package ChildCareTech.model.supplier;

import ChildCareTech.utils.GenericDAO;

public class SupplierDAO extends GenericDAO<Supplier, Integer> {
    public SupplierDAO() {
        super(Supplier.class);
    }
}