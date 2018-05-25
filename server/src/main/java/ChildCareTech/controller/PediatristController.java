package ChildCareTech.controller;

import ChildCareTech.common.DTO.PediatristDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.model.DAO.PediatristDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Pediatrist;
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

public class PediatristController {
    public PediatristController() {}

    public void doSavePediatrist(PediatristDTO pediatristDTO) throws AddFailedException {
        PediatristDAO pediatristDAO = new PediatristDAO();
        PersonDAO personDAO = new PersonDAO();
        Pediatrist pediatrist = EntityFactoryFacade.getEntity(pediatristDTO);
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("fiscalCode", pediatristDTO.getPerson().getFiscalCode());

        pediatristDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            if(personDAO.read(paramMap).isEmpty())
                pediatristDAO.create(pediatrist);
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

    public List<PediatristDTO> doGetAllPediatrists() {
        PediatristDAO pediatristDAO = new PediatristDAO();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        List<PediatristDTO> pediatristDTOList = new ArrayList<>();
        List<Pediatrist> pediatristList = new ArrayList<>();
        Transaction tx = null;
        pediatristDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            pediatristList = pediatristDAO.readAll();
            for(Pediatrist pediatrist : pediatristList)
                pediatristDAO.initializeLazyRelations(pediatrist);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Pediatrist pediatrist : pediatristList) {
            pediatristDTOList.add(DTOFactoryFacade.getDTO(pediatrist));
        }
        return pediatristDTOList;
    }

    public void doRemovePediatrist(PediatristDTO pediatristDTO) {
        PediatristDAO pediatristDAO = new PediatristDAO();
        Pediatrist pediatrist = EntityFactoryFacade.getEntity(pediatristDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        pediatristDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            pediatristDAO.delete(pediatrist);

            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.ADULT);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void doUpdatePediatrist(PediatristDTO pediatristDTO) throws UpdateFailedException{
        PediatristDAO pediatristDAO = new PediatristDAO();
        Pediatrist pediatrist = EntityFactoryFacade.getEntity(pediatristDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        pediatristDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            pediatristDAO.update(pediatrist);

            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.ADULT);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new UpdateFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }
}
