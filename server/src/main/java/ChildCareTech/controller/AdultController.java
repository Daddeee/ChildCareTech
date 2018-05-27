package ChildCareTech.controller;

import ChildCareTech.common.DTO.AdultDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.UserSessionFacade;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.common.exceptions.UpdateFailedException;
import ChildCareTech.model.DAO.AdultDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.entities.Adult;
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

/**
 * Provides implementation for methods in the {@link UserSessionFacade UserSessionFacade} interface
 * that operate with Adult entities.
 */
public class AdultController {
    public AdultController() {}

    /**
     * See {@link UserSessionFacade#saveAdult(AdultDTO)}
     *
     * @param adultDTO
     * @throws AddFailedException
     */
    public void doSaveAdult(AdultDTO adultDTO) throws AddFailedException {
        AdultDAO adultDAO = new AdultDAO();
        PersonDAO personDAO = new PersonDAO();
        Adult adult = EntityFactoryFacade.getEntity(adultDTO);
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("fiscalCode", adultDTO.getPerson().getFiscalCode());

        adultDAO.setSession(session);
        personDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            if(personDAO.read(paramMap).isEmpty())
                adultDAO.create(adult);
            else
                throw new AddFailedException("Una persona con lo stesso codice fiscale è già presente.");

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

    /**
     * See {@link UserSessionFacade#getAllAdults()}
     *
     * @return
     */
    public List<AdultDTO> doGetAllAdults() {
        AdultDAO adultDAO = new AdultDAO();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        List<AdultDTO> adultDTOList = new ArrayList<>();
        List<Adult> adultList = new ArrayList<>();
        Transaction tx = null;
        adultDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            adultList = adultDAO.readAll();
            for(Adult adult : adultList)
                adultDAO.initializeLazyRelations(adult);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Adult adult : adultList) {
            adultDTOList.add(DTOFactoryFacade.getDTO(adult));
        }
        return adultDTOList;
    }

    /**
     * See {@link UserSessionFacade#removeAdult(AdultDTO)}
     *
     * @param adultDTO
     */
    public void doRemoveAdult(AdultDTO adultDTO) {
        AdultDAO adultDAO = new AdultDAO();
        Adult adult = EntityFactoryFacade.getEntity(adultDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        adultDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            adultDAO.delete(adult);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.ADULT);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * See {@link UserSessionFacade#updateAdult(AdultDTO)}
     *
     * @param adultDTO
     * @throws UpdateFailedException
     */
    public void doUpdateAdult(AdultDTO adultDTO) throws UpdateFailedException{
        AdultDAO adultDAO = new AdultDAO();
        Adult adult = EntityFactoryFacade.getEntity(adultDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        adultDAO.setSession(session);
        try{
            tx = session.beginTransaction();
            adultDAO.update(adult);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.ADULT);
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
            throw new UpdateFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * See {@link UserSessionFacade#getAllAdults()}
     *
     * @return
     */
    public List<AdultDTO> doGetAllAdultsExclusive() {
        AdultDAO adultDAO = new AdultDAO();
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        List<AdultDTO> adultDTOList = new ArrayList<>();
        List<Adult> adultList = new ArrayList<>();
        Transaction tx = null;
        adultDAO.setSession(session);

        try{
            tx = session.beginTransaction();

            adultList = adultDAO.readAllExclusive();
            for(Adult adult : adultList)
                adultDAO.initializeLazyRelations(adult);

            tx.commit();
        } catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(Adult adult : adultList) {
            adultDTOList.add(DTOFactoryFacade.getDTO(adult));
        }
        return adultDTOList;
    }
}
