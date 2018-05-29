package ChildCareTech.controller;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.DTO.KidDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.UserSessionFacade;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.model.DAO.*;
import ChildCareTech.model.entities.*;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.network.RemoteEventObservable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Provides implementation for methods in the {@link UserSessionFacade} interface
 * that operate with Kid entities.
 */
public class KidController {
    public KidController() {}

    /**
     * See {@link UserSessionFacade#getAllKids()}
     *
     * @return
     */
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
            kidDTOList.add(DTOFactoryFacade.getDTO(kid));
        }
        return kidDTOList;
    }

    /**
     * See {@link UserSessionFacade#getAvailableKids(TripDTO)}
     *
     * @param tripDTO
     * @return
     */
    public Collection<KidDTO> doGetAvailableKids(TripDTO tripDTO) {
        KidDAO kidDAO = new KidDAO();
        Trip trip = EntityFactoryFacade.getEntity(tripDTO);
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
            kidDTOCollection.add(DTOFactoryFacade.getDTO(k));

        return kidDTOCollection;
    }

    /**
     * See {@link UserSessionFacade#saveKid(KidDTO)}
     *
     * @param kidDTO
     * @throws AddFailedException
     */
    public void doSaveKid(KidDTO kidDTO) throws AddFailedException {
        Kid kid = EntityFactoryFacade.getEntity(kidDTO);
        KidDAO kidDAO = new KidDAO();
        HashMap<String, Object> paramMap = new HashMap<>();
        PersonDAO personDAO = new PersonDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        kidDAO.setSession(session);
        personDAO.setSession(session);
        paramMap.put("fiscalCode", kidDTO.getPerson().getFiscalCode());
        try{
            tx = session.beginTransaction();
            if(personDAO.read(paramMap).isEmpty())
                kidDAO.create(kid);
            else
                throw new AddFailedException("Una persona con lo stesso codice fiscale è già presente.");

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

    /**
     * See {@link UserSessionFacade#removeKid(KidDTO)}
     *
     * @param kidDTO
     */
    public void doRemoveKid(KidDTO kidDTO) {
        KidDAO kidDAO = new KidDAO();
        CheckpointDAO checkpointDAO = new CheckpointDAO();
        TripPartecipationDAO tripPartecipationDAO = new TripPartecipationDAO();

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        kidDAO.setSession(session);
        checkpointDAO.setSession(session);
        tripPartecipationDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            Kid kid = kidDAO.read(kidDTO.getId());
            kidDAO.initializeLazyRelations(kid);

            if(kid.getPerson().getCheckpoints() != null)
                for(Checkpoint c : kid.getPerson().getCheckpoints())
                    checkpointDAO.delete(c);

            if(kid.getPerson().getTripPartecipations() != null)
                for(TripPartecipation t: kid.getPerson().getTripPartecipations())
                    tripPartecipationDAO.delete(t);

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

    /**
     * See {@link UserSessionFacade#updateKid(KidDTO)}
     *
     * @param newKidDTO
     */
    public void doUpdateKid(KidDTO newKidDTO) throws UpdateFailedException {
        KidDAO kidDAO = new KidDAO();
        Kid kid = EntityFactoryFacade.getEntity(newKidDTO);

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
            throw new UpdateFailedException(e.getMessage());
        }
        finally {
            session.close();
        }
    }

    /**
     * See {@link UserSessionFacade#addContactToKid(KidDTO, AdultDTO)}
     *
     * @param kidDTO
     * @param adultDTO
     */
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

    /**
     * See {@link UserSessionFacade#removeContactFromKid(KidDTO, AdultDTO)}
     *
     * @param kidDTO
     * @param adultDTO
     */
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
