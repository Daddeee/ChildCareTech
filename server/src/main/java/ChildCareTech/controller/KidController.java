package ChildCareTech.controller;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.model.DAO.AdultDAO;
import ChildCareTech.model.DAO.KidDAO;
import ChildCareTech.model.entities.Adult;
import ChildCareTech.model.entities.Kid;
import ChildCareTech.model.entities.Trip;
import ChildCareTech.utils.DTO.DTOEntityAssembler;
import ChildCareTech.utils.DTO.DTOFactory;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.utils.RemoteEventObservable;
import ChildCareTech.utils.exceptions.ValidationFailedException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KidController {
    public KidController() {}

    public List<KidDTO> doGetAllKids() {
        KidDAO kidDAO = new KidDAO();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        List<KidDTO> kidDTOList = new ArrayList<>();
        List<Kid> kidList = new ArrayList<>();
        Transaction tx = null;
        kidDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            kidList = kidDAO.readAll();
            for(Kid kid : kidList)
                kidDAO.initializeLazyRelations(kid);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Kid kid : kidList) {
            kidDTOList.add(DTOFactory.getDTO(kid));
        }
        return kidDTOList;
    }

    public Collection<KidDTO> doGetAvailableKids(TripDTO tripDTO) {
        KidDAO kidDAO = new KidDAO();
        Trip trip = DTOEntityAssembler.getEntity(tripDTO);
        List<Kid> kidCollection = new ArrayList<>();
        List<KidDTO> kidDTOCollection = new ArrayList<>();

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        kidDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            kidCollection = kidDAO.getAvailableKids(trip);
            for(Kid k : kidCollection)
                kidDAO.initializeLazyRelations(k);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Kid k : kidCollection)
            kidDTOCollection.add(DTOFactory.getDTO(k));

        return kidDTOCollection;
    }

    public void doSaveKid(KidDTO kidDTO) throws AddFailedException {
        Kid kid = DTOEntityAssembler.getEntity(kidDTO);
        KidDAO kidDAO = new KidDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        kidDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            kidDAO.create(kid);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.KID);
        } catch(Exception ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
            throw new AddFailedException(ex.getMessage());
        } finally {
            session.close();
        }
    }

    public void doRemoveKid(KidDTO kidDTO) {
        KidDAO kidDAO = new KidDAO();
        Kid kid = DTOEntityAssembler.getEntity(kidDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        kidDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            kidDAO.delete(kid);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.KID);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void doUpdateKid(KidDTO newKidDTO) {
        KidDAO kidDAO = new KidDAO();
        Kid kid = DTOEntityAssembler.getEntity(newKidDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        kidDAO.setSession(session);
        try {
            tx = session.beginTransaction();
            kidDAO.update(kid);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.KID);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void doAddContactToKid(KidDTO kidDTO, AdultDTO adultDTO){
        KidDAO kidDAO = new KidDAO();
        AdultDAO adultDAO = new AdultDAO();

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        kidDAO.setSession(session);
        adultDAO.setSession(session);
        try {
            tx = session.beginTransaction();

            Adult adult = adultDAO.read(adultDTO.getId());
            kidDAO.read(kidDTO.getId()).getContacts().add(adult);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void doRemoveContactFromKid(KidDTO kidDTO, AdultDTO adultDTO){
        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        try{
            tx = session.beginTransaction();

            session.createSQLQuery("delete from Kid_Adult where adult_id = :adultId and kid_id = :kidId")
                    .setParameter("adultId", adultDTO.getId())
                    .setParameter("kidId", kidDTO.getId())
                    .executeUpdate();

            tx.commit();
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
