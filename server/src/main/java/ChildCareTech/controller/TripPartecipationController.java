package ChildCareTech.controller;

import ChildCareTech.common.DTO.TripPartecipationDTO;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.UserSessionFacade;
import ChildCareTech.common.exceptions.AddFailedException;
import ChildCareTech.model.DAO.TripPartecipationDAO;
import ChildCareTech.model.entities.TripPartecipation;
import ChildCareTech.utils.DTO.EntityFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.network.RemoteEventObservable;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Provides implementation for methods in the {@link UserSessionFacade UserSessionFacade} interface
 * that operate with TripPartecipation entities.
 */
public class TripPartecipationController {
    public TripPartecipationController() {}

    /**
     * See {@link UserSessionFacade#saveTripPartecipation(TripPartecipationDTO)}
     *
     * @param tripPartecipationDTO
     * @throws AddFailedException
     */
    public void doSaveTripPartecipation(TripPartecipationDTO tripPartecipationDTO) throws AddFailedException {
        TripPartecipation tripPartecipation = EntityFactoryFacade.getEntity(tripPartecipationDTO);
        TripPartecipationDAO tripPartecipationDAO = new TripPartecipationDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        tripPartecipationDAO.setSession(session);

        try{
            tx = session.beginTransaction();
            tripPartecipationDAO.create(tripPartecipation);
            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.TRIPPARTECIPATION);
        } catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new AddFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * See {@link UserSessionFacade#removeTripPartecipation(TripPartecipationDTO)}
     *
     * @param tripPartecipationDTO
     */
    public void doRemoveTripPartecipation(TripPartecipationDTO tripPartecipationDTO) {
        TripPartecipationDAO tripPartecipationDAO = new TripPartecipationDAO();

        TripPartecipation tripPartecipation = EntityFactoryFacade.getEntity(tripPartecipationDTO);

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        tripPartecipationDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            tripPartecipationDAO.delete(tripPartecipation);

            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.TRIPPARTECIPATION);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
