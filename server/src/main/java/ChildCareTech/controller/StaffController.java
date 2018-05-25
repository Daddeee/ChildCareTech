package ChildCareTech.controller;

import ChildCareTech.common.DTO.StaffDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.DAO.StaffDAO;
import ChildCareTech.model.entities.Staff;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.utils.RemoteEventObservable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StaffController {
    public StaffController() {}

    public void doRemoveStaff(StaffDTO staffDTO) {
        StaffDAO staffDAO = new StaffDAO();
        Staff staff = EntityFactoryFacade.getEntity(staffDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        staffDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            staffDAO.delete(staff);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.ADULT);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<StaffDTO> doGetAllStaff() {
        StaffDAO staffDAO = new StaffDAO();
        List<StaffDTO> staffDTOList = new ArrayList<>();
        List<Staff> staffList = new ArrayList<>();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        staffDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            staffList = staffDAO.readAll();
            for(Staff staff : staffList)
                staffDAO.initializeLazyRelations(staff);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Staff staff : staffList) {
            staffDTOList.add(DTOFactoryFacade.getDTO(staff));
        }
        return staffDTOList;
    }

    public void doSaveStaff(StaffDTO staffDTO) throws AddFailedException {
        StaffDAO staffDAO = new StaffDAO();
        PersonDAO personDAO = new PersonDAO();
        Staff staff = EntityFactoryFacade.getEntity(staffDTO);
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("fiscalCode", staffDTO.getPerson().getFiscalCode());

        staffDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            if(personDAO.read(paramMap).isEmpty())
                staffDAO.create(staff);
            else
                throw new AddFailedException("Una persona con lo stesso codice fiscale è già presente");

            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.ADULT);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new AddFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    public void doUpdateStaffMember(StaffDTO staffDTO) throws UpdateFailedException {
        StaffDAO staffDAO = new StaffDAO();
        Staff staff = EntityFactoryFacade.getEntity(staffDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        staffDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            staffDAO.update(staff);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.ADULT);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw  new UpdateFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }
}
