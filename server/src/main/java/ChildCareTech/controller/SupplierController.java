package ChildCareTech.controller;

import ChildCareTech.common.DTO.SupplierDTO;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.DAO.SupplierDAO;
import ChildCareTech.model.entities.Supplier;
import ChildCareTech.utils.DTO.DTOEntityAssembler;
import ChildCareTech.utils.DTO.DTOFactory;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SupplierController {
    public SupplierController() {}

    public void doSaveSupplier(SupplierDTO supplierDTO) throws AddFailedException {
        SupplierDAO supplierDAO = new SupplierDAO();
        PersonDAO personDAO = new PersonDAO();
        Supplier supplier = DTOEntityAssembler.getEntity(supplierDTO);
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        HashMap<String, String> paramMap = new HashMap<>();

        paramMap.put("fiscalCode", supplierDTO.getPerson().getFiscalCode());

        supplierDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            if(personDAO.read(paramMap).isEmpty())
                supplierDAO.create(supplier);
            else
                throw new AddFailedException("Una persona con lo stesso codice fiscale è già presente");

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new AddFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<SupplierDTO> doGetAllSuppliers() {
        SupplierDAO supplierDAO = new SupplierDAO();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        List<Supplier> supplierList = new ArrayList<>();
        Transaction tx = null;
        supplierDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            supplierList = supplierDAO.readAll();
            for(Supplier supplier : supplierList)
                supplierDAO.initializeLazyRelations(supplier);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Supplier supplier : supplierList) {
            supplierDTOList.add(DTOFactory.getDTO(supplier));
        }
        return supplierDTOList;
    }

    public void doRemoveSupplier(SupplierDTO supplierDTO) {
        SupplierDAO supplierDAO = new SupplierDAO();
        Supplier supplier = DTOEntityAssembler.getEntity(supplierDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        supplierDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            supplierDAO.delete(supplier);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
