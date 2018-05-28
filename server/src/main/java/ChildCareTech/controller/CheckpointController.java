package ChildCareTech.controller;

import ChildCareTech.common.DTO.CheckpointDTO;
import ChildCareTech.common.DTO.EventDTO;
import ChildCareTech.common.DTO.TripDTO;
import ChildCareTech.common.EventStatus;
import ChildCareTech.common.EventType;
import ChildCareTech.common.RemoteUpdatable;
import ChildCareTech.common.UserSessionFacade;
import ChildCareTech.common.exceptions.CheckpointFailedException;
import ChildCareTech.model.DAO.CheckpointDAO;
import ChildCareTech.model.DAO.EventDAO;
import ChildCareTech.model.DAO.PersonDAO;
import ChildCareTech.model.DAO.TripDAO;
import ChildCareTech.model.entities.*;
import ChildCareTech.utils.DTO.DTOFactoryFacade;
import ChildCareTech.utils.HibernateSessionFactoryUtil;
import ChildCareTech.network.RemoteEventObservable;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

/**
 * Provides implementation for methods in the {@link UserSessionFacade UserSessionFacade} interface
 * that operate with Checkpoint entities.
 */
public class CheckpointController {
    public CheckpointController() {}

    /**
     * See {@link UserSessionFacade#saveCheckpoint(String, EventDTO, LocalTime)}
     *
     * @param fiscalCode
     * @param eventDTO
     * @param time
     * @throws CheckpointFailedException
     */
    public void doSaveCheckpoint(String fiscalCode, EventDTO eventDTO, LocalTime time) throws CheckpointFailedException {
        PersonDAO personDAO = new PersonDAO();
        Person person;
        EventDAO eventDAO = new EventDAO();
        Event event;
        Checkpoint record;
        CheckpointDAO checkpointDAO = new CheckpointDAO();

        if(eventDTO.getEventType().equals(EventType.TRIP))
            throw new CheckpointFailedException("Evento di tipo gita non supportato");

        if(fiscalCode == null || eventDTO == null || time == null)
            throw new CheckpointFailedException("Uno o più parametri mancanti");

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        personDAO.setSession(session);
        eventDAO.setSession(session);
        checkpointDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            person = personDAO.read("fiscalCode", fiscalCode).get(0);
            if(person == null)
                throw new CheckpointFailedException("Persona non trovata");

            event = eventDAO.read(eventDTO.getId());
            eventDAO.initializeLazyRelations(event);
            if(event == null || !event.getEventStatus().equals(EventStatus.OPEN))
                throw new CheckpointFailedException("Evento non disponibile");

            record = new Checkpoint(event, person, time);
            checkpointDAO.create(record);

            tx.commit();

            RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.CHECKPOINT);
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new CheckpointFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * See {@link UserSessionFacade#saveTripCheckpoint(String, EventDTO, LocalTime, String, TripDTO)}
     *
     * @param fiscalCode
     * @param eventDTO
     * @param time
     * @param busPlate
     * @param tripDTO
     * @throws CheckpointFailedException
     */
    public void doSaveTripCheckpoint(String fiscalCode, EventDTO eventDTO, LocalTime time, String busPlate, TripDTO tripDTO) throws CheckpointFailedException {
        PersonDAO personDAO = new PersonDAO();
        EventDAO eventDAO = new EventDAO();
        TripDAO tripDAO = new TripDAO();
        CheckpointDAO checkpointDAO = new CheckpointDAO();
        Person person;
        Event event;
        Checkpoint record;

        if(fiscalCode == null || eventDTO == null || time == null || busPlate == null || tripDTO == null)
            throw new CheckpointFailedException("Uno o più parametri mancanti");

        if(!eventDTO.getEventType().equals(EventType.TRIP))
            throw new CheckpointFailedException("Evento di tipo diverso da gita non supportato");

        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        Transaction tx = null;
        personDAO.setSession(session);
        eventDAO.setSession(session);
        checkpointDAO.setSession(session);
        tripDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            person = personDAO.read("fiscalCode", fiscalCode).get(0);
            if(person == null)
                throw new CheckpointFailedException("Persona non trovata");

            for(TripPartecipation tripPartecipation : person.getTripPartecipations()){
                if(tripPartecipation.getTrip().getId() == tripDTO.getId()){
                    if(tripPartecipation.getBus().getLicensePlate().equals(busPlate)){
                        event = eventDAO.read(eventDTO.getId());
                        eventDAO.initializeLazyRelations(event);
                        if(event == null || !event.getEventStatus().equals(EventStatus.OPEN))
                            throw new CheckpointFailedException("Evento non disponibile");

                        record = new Checkpoint(event, person, time);
                        checkpointDAO.create(record);
                        tx.commit();
                        RemoteEventObservable.getInstance().notifyObservers(RemoteUpdatable.CHECKPOINT);
                        return;
                    } else {
                        throw new CheckpointFailedException("Bus sbagliato");
                    }
                }
            }

            throw new CheckpointFailedException("Gita non trovata");
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            throw new CheckpointFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * See {@link UserSessionFacade#getEventCheckpoints(EventDTO)}
     *
     * @param eventDTO
     * @return
     */
    public Set<CheckpointDTO> doGetEventCheckpoints(EventDTO eventDTO) {
        Event result;
        EventDTO resultDTO = null;
        EventDAO eventDAO = new EventDAO();

        Transaction tx = null;
        Session session = HibernateSessionFactoryUtil.getInstance().openSession();
        eventDAO.setSession(session);
        try{
            tx = session.beginTransaction();

            result = eventDAO.read(eventDTO.getId());
            eventDAO.initializeLazyRelations(result);
            resultDTO = DTOFactoryFacade.getDTO(result);

            tx.commit();
        } catch(Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return resultDTO == null ? Collections.emptySet() : resultDTO.getCheckpoints();
    }
}
